package orders

class Order(
    val id: Int,
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
        // TODO: add product to _products, ignore null
        product?.let { _products.add(it) }
    }

    /**
     * Removes the first product matching [productId].
     */
    fun removeProductById(productId: Int) {
        // TODO: remove product from _products by id
        _products.removeIf { it.id == productId }
    }

    /**
     * Returns the total price of all products in the order.
     */
    override fun calculateTotal(): Int {
        // TODO: sum the prices of all products
        return _products.sumOf { it.price }
    }

    /**
     * Marks the order as paid.
     * Throws [IllegalStateException] if the order has no products.
     */
    fun pay() {
        // TODO: throw if _products is empty, otherwise set status to Paid
        if (_products.isEmpty()) throw IllegalStateException()
        status = OrderStatus.Paid
    }

    /**
     * Cancels the order with the given reason.
     * If [reason] is null, use "Unknown reason".
     */
    fun cancel(reason: String?) {
        // TODO: set status to Cancelled with reason (default "Unknown reason" if null)
        status = OrderStatus.Cancelled(reason ?: "Unknown reason")
    }
}
