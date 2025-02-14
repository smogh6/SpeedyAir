public class Order implements Comparable<Order> {
    private final int orderId;
    private String orderOrigin;
    private String orderDestination;

    public Order(int orderId, String orderOrigin, String orderDestination) {
        this.orderId = orderId;
        this.orderOrigin = orderOrigin;
        this.orderDestination = orderDestination;
    }

    public int getOrderId() {
        return orderId;
    }
    public String getOrderDestination() {
        return orderDestination;
    }

    /**
     * Overriden compareTo method for order. Comparision is based on orderId.
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Order o) {
        return Integer.compare(o.orderId, this.orderId);
    }
}
