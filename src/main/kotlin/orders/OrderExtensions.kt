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

    val currentProducts = products

    currentProducts.forEach { product ->
        val discount = (100 - discountPercent) / 100.0
        val newPrice = (product.price * discount).toInt()

        val updatedProduct = product.copy(price = newPrice)

        logger?.invoke(
            "Product '${product.name}': ${product.price} -> ${updatedProduct.price} ($discountPercent% discount)"
        )

        removeProductById(product.id)
        addProduct(updatedProduct)
    }
}
