import java.util.List;

public abstract class FlightInventoryService {
    FlightScheduler flightScheduler;

    public void outputOrders(){}

    public FlightInventoryService(FlightScheduler flightScheduler) {
        this.flightScheduler = flightScheduler;
    }

    public void processOrders(String filePath) {
    }
}
