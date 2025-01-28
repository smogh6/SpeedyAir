import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public abstract class FlightScheduler {
    private List<Flight> flightList = new ArrayList<>();


    /**
     * Method to add a single flight object to the schedule
     * @param flight
     */
    public void addFlight(Flight flight) {}

    /**
     * Method to output all the flights on the schedule
     */
    public void outputSchedule() {}
    public List<Flight> getFlightList() {
        return flightList;
    }

}
