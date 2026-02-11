package orders

/**
 * Applies a percentage discount to every product in the order.
 *
 * Hint: use [products] to read the current list, [removeProductById] and
 * [addProduct] to replace each product with a discounted copy.
 * Use [Product.copy] to create a new product with a modified price.
 *
 * @param discountPercent discount percentage (e.g. 10 means 10%)
 * @param logger optional callback invoked with a log message for each product
 */
fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    products.forEach { product ->
        val newPrice = product.price - ((discountPercent / 100f) * product.price).toInt()
        val newProduct = product.copy(price = newPrice.coerceAtLeast(0))
        removeProductById(newProduct.id)
        addProduct(newProduct)
        logger?.let {
            it("Price of ${product.name} is now $newPrice")
        }
    }
}
