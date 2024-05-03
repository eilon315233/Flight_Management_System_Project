import java.util.List;
/*
    This class represents the search strategy for searching flights by duration time.
    It implements the searchStrategy interface.
 */
public class DurationFlight_Search_Strategy implements searchStrategy{
    //search method that searches for flights by duration time
    @Override
    public void search(List<Flight> flights, String search) {
        System.out.println("Searching for flight with duration " + search + "...");
        System.out.println(" ");
        System.out.println("Flights with duration " + search + ":");
        for (Flight flight : flights)
        {
            if (flight.getDurationFlight().equals(search))
            {
                System.out.println("Flight with ID " + flight.getFlightID() + " has been found with duration " + search);
            }
            //if the flight is not found
            else {
                System.out.println("Flight with duration " + search + " not found");
            }
        }
    }
}
