package orders

@FunctionalInterface
interface PriceCalculator {
    fun calculateTotal(): Int
}
