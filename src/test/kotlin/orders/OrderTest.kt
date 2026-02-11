package orders

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.UnsupportedOperationException

class OrderTest {

    @Test
    fun `addProduct ignores null`() {
        val order = Order(1)
        order.addProduct(null)

        assertEquals(0, order.calculateTotal())
    }

    @Test
    fun `calculate total sum`() {
        val order = Order(1)
        order.addProduct(Product(1, "A", 100))
        order.addProduct(Product(2, "B", 200))

        assertEquals(300, order.calculateTotal())
    }

    @Test
    fun `remove product by id`() {
        val order = Order(1)
        order.addProduct(Product(1, "A", 100))
        order.addProduct(Product(2, "B", 200))

        order.removeProductById(1)

        assertEquals(200, order.calculateTotal())
    }

    @Test
    fun `remove only first product when product id repeats`() {
        val order = Order(1)
        val product1 = (Product(1, "A", 100))
        val product2 = (Product(1, "B", 100))
        order.addProduct(product1)
        order.addProduct(product2)

        order.removeProductById(product1.id)

        assertTrue(order.products.contains(product2))
    }

    @Test
    fun `pay empty order throws exception`() {
        val order = Order(1)

        assertThrows(IllegalStateException::class.java) {
            order.pay()
        }
    }

    @Test
    fun `pay changes status`() {
        val order = Order(1)
        order.addProduct(Product(1, "A", 100))

        order.pay()

        assertTrue(order.status is OrderStatus.Paid)
    }

    @Test
    fun `cancel with null reason`() {
        val order = Order(1)
        order.cancel(null)

        val status = order.status as OrderStatus.Cancelled
        assertEquals("Unknown reason", status.reason)
    }

    @Test
    fun `status gives correct reason after cancel`() {
        val order = Order(1)
        val cancelReason = "I don't have money"
        order.cancel(cancelReason)

        val status = order.status as OrderStatus.Cancelled
        assertEquals(cancelReason, status.reason)
    }

    @Test
    fun `process order with when`() {
        val order = Order(1)
        order.addProduct(Product(1, "A", 100))
        order.pay()

        val result = processOrder(order)

        assertEquals("Order 1 is paid", result)
    }

    @Test
    fun `apply discount reduces price`() {
        val order = Order(1)
        order.addProduct(Product(1, "A", 100))

        order.applyDiscount(10)

        assertEquals(90, order.calculateTotal())
    }

    @Test
    fun `apply discount with logger`() {
        val logs = mutableListOf<String>()
        val order = Order(1)
        order.addProduct(Product(1, "A", 100))

        order.applyDiscount(10) {
            logs.add(it)
        }

        assertTrue(logs.isNotEmpty())
    }

    @Test
    fun `when discount is more than 100 percent, new price is 0`() {
        val order = Order(1)
        order.addProduct(Product(1, "A", 150))

        order.applyDiscount(155)

        assertEquals(0, order.calculateTotal())
    }

    @Test
    fun `product list is empty after products in order are paid`() {
        val order = Order(1)
        order.addProduct(Product(1, "A", 100))
        order.addProduct(Product(2, "B", 200))

        order.pay()

        assertTrue(order.products.isEmpty())
    }

    @Test
    fun `Can't add products to order without method addProduct`(){
        val order = Order(1)
        order.addProduct(Product(1, "A", 100))

        assertThrows(UnsupportedOperationException::class.java) {
            (order.products as MutableList).add(Product(1,"A",100))
        }
    }
}