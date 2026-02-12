package orders

private const val payEmptyOrderExc = "Cannot pay for an empty order"
private const val addEmptyProd = "Empty product cannot be added to the order"

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
        }else{
            println(addEmptyProd)
        }
    }

    /**
     * Removes the first product matching [productId].
     */
    fun removeProductById(productId: Int) {
        _products.forEach {
            if (it.id == productId) {
                _products.remove(it)
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
            throw IllegalStateException(payEmptyOrderExc)
        } else {
            status = OrderStatus.Paid
        }
    }

    /**
     * Cancels the order with the given reason.
     * If [reason] is null, use "Unknown reason".
     */
    fun cancel(reason: String?) {
        this.status = OrderStatus.Cancelled(reason ?: "Unknown reason")
    }
}
