public class Main {
    public static void main(String[] args) {
        String FLIGHT_FILE = "flight.txt";
        String ORDERS_FILE = "coding-assigment-orders.json";

        FlightScheduler fs = new FlightSchedulerImpl(FLIGHT_FILE);
        FlightInventoryService fis = new FlightInventoryServiceImpl(fs, ORDERS_FILE);

        // User story 1
        fs.outputSchedule();

        //User Story 2
        fis.outputOrders();


    }
}