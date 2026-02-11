package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()

    /** Read-only view of products in the order. */
    val products: List<Product> get() = _products.toList()

    var status: OrderStatus = OrderStatus.Created
        private set

    /**
     * Adds a product to the order.
     * If the product is null, it should be ignored.
     */
    fun addProduct(product: Product?) {
        if (product != null) {
            _products.add(product)
        }
    }

    /**
     * Removes the first product matching [productId].
     */
    fun removeProductById(productId: Int) {
        val index = _products.indexOfFirst { it.id == productId }
        if (index != -1) {
            _products.removeAt(index)
        }
    }

    /**
     * Returns the total price of all products in the order.
     */
    override fun calculateTotal(): Int {
        var total = 0
        for (product in _products) {
            total += product.price
        }
        return total
    }

    /**
     * Marks the order as paid.
     * Throws [IllegalStateException] if the order has no products.
     */
    fun pay() {
        if (_products.size == 0) {
            throw IllegalStateException("Order has no products")
        } else {
            status = OrderStatus.Paid
        }
    }

    /**
     * Cancels the order with the given reason.
     * If [reason] is null, use "Unknown reason".
     */
    fun cancel(reason: String?) {
        if (reason == null) {
             status = OrderStatus.Cancelled("Unknown reason")
        } else {
            status = OrderStatus.Cancelled(reason)
        }
    }
}
