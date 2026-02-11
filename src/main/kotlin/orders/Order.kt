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
        product?.let { _products.add(it) }
    }

    /**
     * Removes the first product matching [productId].
     */
    fun removeProductById(productId: Int) {
        if (_products.isNotEmpty()) {
            _products.removeIf { it.id == productId }
        }
    }

    /**
     * Returns the total price of all products in the order.
     */
    override fun calculateTotal(): Int {
        if (_products.isNotEmpty()) {
            val sum = _products.sumOf { it.price }
            return sum
        }
        return 0
    }

    /**
     * Marks the order as paid.
     * Throws [IllegalStateException] if the order has no products.
     */
    fun pay() {
        if (_products.isNotEmpty()) {
            status = OrderStatus.Paid
        } else throw IllegalStateException()
    }

    /**
     * Cancels the order with the given reason.
     * If [reason] is null, use "Unknown reason".
     */
    fun cancel(reason: String?) {
        val msg = reason ?: run { "Unknown reason" }
        status = OrderStatus.Cancelled(msg)
    }
}
