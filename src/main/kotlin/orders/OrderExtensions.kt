package orders

/**
 * Применяем процентную скидку к каждому товару в заказе
 */
fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    this.products.forEach { product ->
        // Удаляем старый товар
        this.removeProductById(product.id)

        // Считаем новую цену товара
        val newPrice = product.price * (100 - discountPercent) / 100

        // Создаем товар с новой ценой, используя метод copy data-класса
        val discountedProduct = product.copy(price = newPrice)

        // Возвращаем товар
        this.addProduct(discountedProduct)

        // Логируем, если логгер передан
        logger?.invoke("Product ${product.name} discounted to $newPrice")
    }
}
