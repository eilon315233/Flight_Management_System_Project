import java.util.List;

/*
    This class represents the search context, which uses the search strategy.
 */
public class SearchContext {
    private searchStrategy strategy;

    //Constructor
    public SearchContext(searchStrategy strategy) {
        this.strategy = strategy;
    }

    //Methods

    //This method sets the search strategy
    public void setSearchStrategy(searchStrategy strategy) {
        this.strategy = strategy;
    }

    //This method uses the search strategy
    public void TypeOfSearch(List<Flight> flights, String search) {strategy.search(flights, search);}
}