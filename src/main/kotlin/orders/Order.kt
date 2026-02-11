package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()

    val products: List<Product> get() = _products.toList()

    var status: OrderStatus = OrderStatus.Created
        private set

    fun addProduct(product: Product?) = product?.let { product ->
        _products.add(product)
    }

    fun removeProductById(productId: Int) {
        _products.removeIf { product ->
            product.id == productId
        }
    }

    override fun calculateTotal(): Int {
        var sum = 0
        for (product in _products) {
            sum += product.price
        }
        return sum
    }

    fun pay() {
        if (_products.isNotEmpty()) {
            status = OrderStatus.Paid
        } else {
            throw IllegalStateException()
        }
    }

    fun cancel(reason: String?) {
        status = OrderStatus.Cancelled(reason = reason ?: "Unknown reason")
    }
}
