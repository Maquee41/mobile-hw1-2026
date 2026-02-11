package orders

fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    products.forEach { product ->
        val newPrice = product.price - (product.price * discountPercent / 100)
        val discountedProduct = product.copy(price = newPrice)
        removeProductById(product.id)
        addProduct(discountedProduct)
        logger?.invoke("Discount applied to ${discountedProduct.name}: ${product.price} -> ${discountedProduct.price}")
    }
}
