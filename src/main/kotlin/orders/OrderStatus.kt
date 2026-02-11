package orders

sealed interface OrderStatus<out T> {
    data object Created : OrderStatus<Nothing>
    data object Paid : OrderStatus<Nothing>
    data class Cancelled<T>(val reason: T) : OrderStatus<T>
}
