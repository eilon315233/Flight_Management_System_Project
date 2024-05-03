import java.util.List;

/*
    This class represents the search strategy for searching flights by price.
    It implements the searchStrategy interface.
 */

public class Price_Search_Strategy implements searchStrategy
{

    //search method that searches for flights by price
    @Override
    public void search(List<Flight> flights, String price) {
        System.out.println("Searching for flight with price " + price + "$...");
        System.out.println(" ");

        for (Flight flight : flights)
        {
            if (flight.getPrice() <= Double.parseDouble(price))
            {
                System.out.println("Flight with ID " + flight.getFlightID() + " has been found with price " + price + "$");
            }
            //if the flight is not found
            else {
                System.out.println("Flight with price " + price + "$ not found");
            }
        }


    }
}
