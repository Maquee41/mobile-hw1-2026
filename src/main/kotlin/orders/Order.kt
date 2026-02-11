package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()

    /** Read-only view of products in the order. */
    val products: List<Product> get() = _products.toList()

    var status: OrderStatus<String> = OrderStatus.Created
        private set

    /**
     * Adds a product to the order.
     * If the product is null, it should be ignored.
     */
    fun addProduct(product: Product?) {
        product?.let {
            _products.add(it)
        }
    }

    /**
     * Removes the first product matching [productId].
     */
    fun removeProductById(productId: Int) {
        _products.forEach { product ->
            if (product.id == productId) {
                _products.remove(product)
                return
            }
        }
    }

    /**
     * Returns the total price of all products in the order.
     */
    override fun calculateTotal(): Int {
        return _products.sumOf { it.price }
    }

    /**
     * Marks the order as paid.
     * Throws [IllegalStateException] if the order has no products.
     */
    fun pay() {
        if (_products.isEmpty()) {
            throw IllegalStateException(EMPTY_LIST)
        } else {
            status = OrderStatus.Paid
            _products.removeAll(_products)
        }
    }

    /**
     * Cancels the order with the given reason.
     * If [reason] is null, use "Unknown reason".
     */
    fun cancel(reason: String?) {
        status = if (reason != null) {
            OrderStatus.Cancelled(reason)
        } else {
            OrderStatus.Cancelled(CANCELLATION_UNKNOWN_REASON)
        }
    }

    private companion object {
        const val CANCELLATION_UNKNOWN_REASON = "Unknown reason"
        const val EMPTY_LIST = "The list is empty, add products to your order"
    }
}
