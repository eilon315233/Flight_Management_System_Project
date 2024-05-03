import java.util.List;
/*
    This interface represents the search strategy for searching flights.
 */

public interface searchStrategy {
    public void search(List<Flight> flights, String search); //search method that searches for flights
}
