package orders

fun processOrder(order: Order) = when (order.status) {
    OrderStatus.Created -> "Order ${order.id} is new"
    OrderStatus.Paid -> "Order ${order.id} is paid"
    is OrderStatus.Cancelled -> "Order ${order.id} is cancelled: ${order.status}"
}