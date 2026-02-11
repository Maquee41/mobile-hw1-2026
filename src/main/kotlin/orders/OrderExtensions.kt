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
    logger: ((String) -> Unit)? = null,
) {
    // TODO: apply discount to each product using extension + scoped functions
    val discount = (100 - discountPercent) / 100.0
    products.forEach { product ->
        val discountProduct = product.copy(
            price = (product.price * discount).toInt()
        )
        logger?.invoke("$product -> $discountProduct")

        removeProductById(product.id)
        logger?.invoke("Removed $product")
        addProduct(discountProduct)
        logger?.invoke("Added $discountProduct")

    }
}
