import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FlightInventoryServiceImpl extends FlightInventoryService{
    private final String ORIGIN =  "YUL";
    List<Order> orders = new ArrayList<>();
    SortedMap<Order, Flight> orderMap = new TreeMap<>();
    public FlightInventoryServiceImpl(FlightScheduler flightScheduler, String fileName) {
        super(flightScheduler);
    }

    /**
     * Adds all the orders from a given Json file into the orders list.
     * @param filePath of a file containing json
     */
    @Override
    public void processOrders(String filePath) {
        StringBuilder jsonOrdersSB = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonOrdersSB.append(line.trim());
            }
            String jsonOrders = jsonOrdersSB.toString();
            this.orders.addAll(jsonToOrders(jsonOrders));

        } catch (IOException e) {
            System.err.println("An error occurred reading the input JSON file");
            throw new RuntimeException(e);
        }

    }
    private List<Order> jsonToOrders(String jsonOrders) {
        jsonOrders = jsonOrders.substring(1, jsonOrders.length() - 1); // Remove the outer `{}`

        String[] orderEntries = jsonOrders.split("},"); // Split by closing braces
        List<Order> orders = new ArrayList<>();

        for (String entry : orderEntries) {
            // Ensure each entry is properly formatted
            if (!entry.endsWith("}")) {
                entry = entry + "}";
            }

            // Extract the order ID
            int orderIdStart = entry.indexOf("order-") + 6;
            int orderIdEnd = entry.indexOf("\"", orderIdStart);
            int orderId = Integer.parseInt(entry.substring(orderIdStart, orderIdEnd));

            // Extract the destination
            int destinationStart = entry.indexOf("\"destination\" : \"") + 17;
            int destinationEnd = entry.indexOf("\"", destinationStart);
            String destination = entry.substring(destinationStart, destinationEnd);

            // Create the Order object with orderOrigin as "YUL"
            Order order = new Order(orderId, ORIGIN, destination);
            orders.add(order);
            assignOrder(order);
        }
        return orders;
    }

    /**
     * Assigns an order to a particular flight if available
     * @param order
     */
    public void assignOrder(Order order) {
        if (!orderMap.containsKey(order)) {
            Flight flight = getAvailableFlight(order);
            if (flight != null) {
                flight.addFreight(order);
                orderMap.put(order,flight);
            }
        }
    }

    /**
     * Private method to return possible flights with available capacity
     * @param order
     * @return
     */
    private Flight getAvailableFlight(Order order) {
        List<Flight> flights = this.flightScheduler.getFlightList();
        if (flights !=null){
            for (Flight f: flights) {
                if (f.getDeparture().equals(ORIGIN) && f.getArrival().equals(order.getOrderDestination()) && f.getFreight().size() < f.MAXSIZE) {
                    return f;
            }
        }}
        return null;
    }

    @Override
    public void outputOrders() {
        for (Order order: this.orders) {
            String output;
            if (orderMap.containsKey(order)) {
                Flight flight = orderMap.get(order);
                output = String.format("order: order-%03d, flightNumber: %d, departure: %s, arrival: %s, day: %d",
                        order.getOrderId(),flight.getFlightId(),flight.getDeparture(),flight.getArrival(),flight.getDay());
            }
            else {
                output = String.format("order: order-%03d, flightNumber: not scheduled", order.getOrderId());
            }
            System.out.println(output);
        }
    }
}
