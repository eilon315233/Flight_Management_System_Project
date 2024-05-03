import java.util.List;

/*
    This class represents the search strategy for searching flights by departure time.
    It implements the searchStrategy interface.
 */
public class DepartureTime_Search_Strategy implements searchStrategy
{
    //search method that searches for flights by departure time
    @Override
    public void search(List<Flight> flights, String departureTime) {
        System.out.println("Searching for flight with departure time " + departureTime + "...");
        System.out.println(" ");

        System.out.println("Flights with departure time " + departureTime + ":");
        for (Flight flight : flights)
        {
            if (flight.getDepartureTime().equals(departureTime))
            {
                System.out.println("Flight with ID " + flight.getFlightID() + " has been found with departure time " + departureTime);
            }
            //if the flight is not found
            else {
                System.out.println("Flight with departure time " + departureTime + " not found");
            }
        }

    }
}
