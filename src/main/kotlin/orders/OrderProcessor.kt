package orders

/**
 * Возвращает понятное описание статусов заказов
 *   - Created  -> "Order {id} is new"
 *   - Paid     -> "Order {id} is paid"
 *   - Cancelled -> "Order {id} is cancelled: {reason}"
 */
fun processOrder(order: Order): String {
    return when (val s = order.status) {
        OrderStatus.Created -> "Order ${order.id} is new"
        OrderStatus.Paid -> "Order ${order.id} is paid"
        is OrderStatus.Cancelled -> "Order ${order.id} is cancelled: ${s.reason}"
    }
}
