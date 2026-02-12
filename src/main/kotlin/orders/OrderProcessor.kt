package orders

/**
 * Returns a human-readable description of the order status.
 * Use a `when` expression on [order].status:
 *   - Created  -> "Order {id} is new"
 *   - Paid     -> "Order {id} is paid"
 *   - Cancelled -> "Order {id} is cancelled: {reason}"
 */
fun processOrder(order: Order): String {
    // TODO: use when to return the appropriate string
    return when (order.status) {
        OrderStatus.Created -> "Order ${order.id} is new"
        is OrderStatus.Cancelled -> "Order ${order.id} cancelled: ${(order.status as OrderStatus.Cancelled).reason}"
        OrderStatus.Paid -> "Order ${order.id} is paid"
    }
}
