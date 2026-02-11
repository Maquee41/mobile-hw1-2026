package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()

    /** Список только для чтения извне */
    val products: List<Product> get() = _products.toList()

    var status: OrderStatus = OrderStatus.Created
        private set

    /** Добавляем товар, если он не пустой */
    fun addProduct(product: Product?) {
        // Если товар не null, то выполняется код внутри let
        product?.let { _products.add(it) }
    }

    /**
     * Ищем первое вхождение с указанным id и удаляем этот товар
     */
    fun removeProductById(productId: Int) {
        val product = _products.find { it.id == productId }
        product?.let {
            _products.remove(it)
        }
    }

    /**
     * Находим и возвращаем суммарную цену всех товаров
     */
    override fun calculateTotal(): Int {
        val totalPrice = _products.sumOf { it.price }
        return totalPrice
    }

    /**
     * Помечаем заказ оплаченным, или выбрасываем исключние, если он пустой
     */
    fun pay() {
        if (_products.isEmpty()) {
            throw IllegalStateException("Cannot pay for an empty order")
        }
        status = OrderStatus.Paid
    }

    /**
     * Отменяем заказ с указанной причиной, или с неизвестной причиной
     */
    fun cancel(reason: String?) {
        status = OrderStatus.Cancelled(reason = reason ?: "Unknown reason")
    }
}
