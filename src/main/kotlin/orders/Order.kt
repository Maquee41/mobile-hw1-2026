package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()
    val products: List<Product> get() = _products.toList()

    var status: OrderStatus = OrderStatus.Created
        private set

    fun addProduct(product: Product?) {
        if (product == null) return
        _products.add(product)
    }

    fun removeProductById(productId: Int) {
        _products.removeIf { it.id == productId }
    }

    override fun calculateTotal(): Int {
        return _products.sumOf { it.price }
    }

    fun pay() {
        if (_products.isEmpty()) throw IllegalStateException()
        status = OrderStatus.Paid
    }

    fun cancel(reason: String?) {
        status = OrderStatus.Cancelled(reason ?: "Unknown reason")
    }
}
