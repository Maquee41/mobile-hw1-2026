package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()
    val products: List<Product>
        get() = _products.toList()

    var status: OrderStatus = OrderStatus.Created
        private set

    fun addProduct(product: Product?) {
        product?.let { _products.add(it) }
    }

    fun removeProductById(productId: Int) {
        _products.forEach {
            if (it.id == productId)
                _products.remove(it)
        }
    }

    override fun calculateTotal(): Int {
        var sum = 0
        _products.forEach { sum += it.price }
        return sum
    }

    /**
     * Marks the order as paid.
     * Throws [IllegalStateException] if the order has no products.
     */
    fun pay() {
        // TODO: throw if _products is empty, otherwise set status to Paid
        check(_products.isNotEmpty()) { "There is no products in the order!" }
        /*if (_products.isEmpty()) {
            throw IllegalStateException("There is no products in the order!")
        }*/

        if (status == OrderStatus.Created) status = OrderStatus.Paid
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
