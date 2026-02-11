package orders

/**
 * Returns a human-readable description of the order status.
 */
fun processOrder(order: Order): String {
    return when(val status = order.status){
        is OrderStatus.Created -> "Order ${order.id} is new"
        is OrderStatus.Paid -> "Order ${order.id} is paid"
        is OrderStatus.Cancelled -> "Order ${order.id} is cancelled: ${status.reason}"
    }
}
