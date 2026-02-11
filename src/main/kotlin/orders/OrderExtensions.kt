package orders

/**
 * Applies a percentage discount to every product in the order.
 *
 * @param discountPercent discount percentage (e.g. 10 means 10%)
 * @param logger optional callback invoked with a log message for each product
 */
fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    products.forEach { product ->
        val newPrice = (product.price * (100 - discountPercent) / 100).toInt()

        removeProductById(product.id)
        addProduct(product.copy(price = newPrice))
        logger?.invoke("The new price of the ${product.name} is now $newPrice")
    }
}
