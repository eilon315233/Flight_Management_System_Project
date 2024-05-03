import java.util.List;
/*
    This class represents the search strategy for searching flights by ID.
    It implements the searchStrategy interface.
 */

public class FlightID_Search_Strategy implements searchStrategy{

    //search method that searches for flights by ID
    @Override
    public void search(List<Flight> flights, String ID) {
        System.out.println("Searching for flight with ID " + ID + "...");
        System.out.println(" ");
        for (Flight flight : flights)
        {
            if (flight.getFlightID().equals(ID))
            {
                System.out.println("Flight with ID " + flight.getFlightID() + " has been found");
                System.out.println("Deatils of the flight: Duration: " + flight.getDurationFlight() + " Departure Time: " + flight.getDepartureTime() + " Price: " + flight.getPrice() + "$");
            }
            //if the flight is not found
            else {
                System.out.println("Flight with ID " + ID + " not found");
            }
        }
    }
}
