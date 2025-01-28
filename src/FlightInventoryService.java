/**
 * Abstract class which manages all the state for orders
 */
public abstract class FlightInventoryService {
    FlightScheduler flightScheduler;

    public void outputOrders(){}

    public FlightInventoryService(FlightScheduler flightScheduler) {
        this.flightScheduler = flightScheduler;
    }

    public void processOrders(String filePath) {
    }
}
