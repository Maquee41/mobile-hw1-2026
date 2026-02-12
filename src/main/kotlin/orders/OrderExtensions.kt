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
    val discountedProducts = products.map {
        val discount = (it.price * (discountPercent.toDouble() / 100)).toInt()
        logger?.invoke("Discount for product ${it.id} = $discount")
        it.copy(price = it.price - discount)
    }

    discountedProducts.forEach {
        this.removeProductById(it.id)
        this.addProduct(it)
    }
}
