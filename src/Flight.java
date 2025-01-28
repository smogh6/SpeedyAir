import java.util.ArrayList;
import java.util.List;

/**
 * A pojo for our Flight model.
 */
public class Flight implements Comparable<Flight> {
    private final int flightId;
    private String departure;
    private String arrival;
    private int day;
    private List<Order> freight = new ArrayList<>();
    public final int MAXSIZE = 20;

    public Flight(int flightId, String departure, String arrival, int day) {
        this.flightId = flightId;
        this.departure = departure;
        this.arrival = arrival;
        this.day = day;
    }

    public int getFlightId() {
        return flightId;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public int getDay() {
        return day;
    }

    public List<Order> getFreight() {
        return freight;
    }

    /**
     * Adds an object to this flight's freight
     * @param order
     */
    public void addFreight(Order order) {
        this.freight.add(order);
    }

    /**
     * A comparator for Flight objects based on the day they are scheduled.
     * @param f the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Flight f) {
        return Integer.compare(this.day, f.day);
    }
}
