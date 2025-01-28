import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FlightSchedulerImpl extends FlightScheduler {

    public FlightSchedulerImpl(String fileName) {
        processFlights(fileName);
    }

    @Override
    public void addFlight(Flight flight) {
        this.getFlightList().add(flight);
    }


    /**
     * This implementation will take in a txt file containing the schedule as outlined in the input example provided.
     * It will process all the lines of text containing flight information and create a flight schedule.
     * @param flightsFile
     */
    private void processFlights(String flightsFile) {
        int day = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(flightsFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Day")) {
                    day = Integer.parseInt(line.split(" ")[1].replace(":", ""));
                } else if (line.startsWith("Flight")) {
                    // Extract flight information sample line Flight 1: Montreal airport (YUL) to Toronto (YYZ)
                    String[] parts = line.split(" ");
                    int flightId = Integer.parseInt(parts[1].replace(":", ""));

                    /* This if else clause is due to the inconsistency of input.
                            Flight 1: Montreal airport (YUL) to Toronto (YYZ)
                            Flight 2: Montreal (YUL) to Calgary (YYC)
                        The above 2 examples are both valid. Thus we augment our logic based on input line.

                     */
                    String departure = null; String arrival = null;
                    if (parts[3].equals("airport")) {
                        departure = parts[4].replace("(", "").replace(")", "").trim();
                        arrival = parts[7].replace("(", "").replace(")", "").trim();
                    } else {
                        departure = parts[3].replace("(", "").replace(")", "").trim();
                        arrival = parts[6].replace("(", "").replace(")", "").trim();
                    }
                    // Create Flight object and add it to the list
                    this.addFlight(new Flight(flightId, departure, arrival, day));
                }
            }
        } catch (IOException e) {
            System.err.println("Error during file read");
            throw new RuntimeException(e);
        }
    }
    @Override
    public void outputSchedule() {
        for (Flight f : this.getFlightList()) {
            String flightString = String.format("Flight: %d, departure: %s, arrival %s, day %d",
                    f.getFlightId(), f.getDeparture(), f.getArrival(), f.getDay());
            System.out.println(flightString);
        }
    }
}
