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

    public String getOrderOrigin() {
        return orderOrigin;
    }

    public void setOrderOrigin(String orderOrigin) {
        this.orderOrigin = orderOrigin;
    }

    public String getOrderDestination() {
        return orderDestination;
    }

    public void setOrderDestination(String orderDestination) {
        this.orderDestination = orderDestination;
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
