package orders

fun processOrder(order: Order): String {
    return when(order.status){
        OrderStatus.Created -> "Order ${order.id} is created"
        OrderStatus.Paid -> "Order ${order.id} is paid"
        is OrderStatus.Cancelled -> "Order ${order.id} is cancelled"
    }
}
