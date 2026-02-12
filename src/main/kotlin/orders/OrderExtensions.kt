package orders

/**
 * Applies a percentage discount to every product in the order.
 *
 * Hint: use [Order.products] to read the current list, [Order.removeProductById] and
 * [Order.addProduct] to replace each product with a discounted copy.
 * Use [Product.copy] to create a new product with a modified price.
 *
 * @param discountPercent discount percentage (e.g. 10 means 10%)
 * @param logger optional callback invoked with a log message for each product
 */
fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    // Try to use scoped function .also
    products.forEach { product ->
        val discountedPrice = product.price * (100 - discountPercent) / 100

        product.copy(price = discountedPrice)
            .also { discountedProduct ->
                removeProductById(product.id)
                addProduct(discountedProduct)
            }
            .also { discountedProduct ->
                logger?.invoke(
                    "Applied $discountPercent% discount to product ${product.id} (${product.name}): " +
                            "${product.price} -> ${discountedProduct.price}"
                )
            }
        }
}
