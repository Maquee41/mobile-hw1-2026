package orders

fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    var newPrice: Int?
    var newProduct: Product?

    products.forEach { product ->
        newPrice = (product.price * (100 - discountPercent) / 100.0).toInt()
        newProduct = product.copy(
            price = newPrice
        )
        
        removeProductById(product.id)
        addProduct(newProduct)
        logger?.invoke("${product.name}'s price changed to ${newProduct.price}")
    }
}
