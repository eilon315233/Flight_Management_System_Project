import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
   This class represents an airline company that can contain flights and other airlines companies
   based on the composite pattern. It also implements the Observable interface to notify the observers
   about updates.
*/
public class Airline implements Airport_Composite, Observable{

    //variables
    private String name; //name of the airline
    private List<Airport_Composite> airportComponentsList; //list of airport components (flights and other airlines)
    private final HashSet<Observer> observers = new HashSet<Observer>(); //set of observers

    //constructor
    public Airline(String name)
    {
        this.name = name;
        this.airportComponentsList = new ArrayList<Airport_Composite>();
    }

    //methods
    public String getName() {return name;}
    public List<Airport_Composite> getAirportComponentsList() {return this.airportComponentsList;} //returns the list of airport components

    //add a new flight to the airport composite list as a leaf
    public Flight addFlightToAirportComposite(String flightID, String durationFlight, String departureTime, double price)
    {
        for (Airport_Composite airportComponent : airportComponentsList)
        {
            if (airportComponent instanceof Flight)
            {
                if (((Flight) airportComponent).getFlightID().equals(flightID)) //check if the flight already exists
                {
                    System.out.println("Flight with ID " + flightID + " already exists");
                    return null;
                }
            }
        }
        Flight flight = new Flight(flightID, durationFlight, departureTime, price); //create a new flight
        airportComponentsList.add(flight);
        System.out.println("Flight with ID " + flightID + " has been successfully added");
        return flight;
    }

    // Composite pattern implementation

    //add a component to the airport composite
    @Override
    public void add(Airport_Composite airportComponent) { airportComponentsList.add(airportComponent);}

    //remove a component from the airport composite
    @Override
    public void remove(Airport_Composite airportComponent) { airportComponentsList.remove(airportComponent);}

    //display the airport composite by displaying all the components
    @Override
    public void display() {
        sortAirportComponentsList(); //sort the list of airport components
        System.out.println("Airline: " + name);
        for (Airport_Composite airportComponent : airportComponentsList)
        {
            airportComponent.display();
        }
    }


    // Observer pattern implementation

    //add an observer to the list of observers
    @Override
    public void addObserver(Observer observer) {
        for (Observer o : observers)
        {
            if (o.getID().equals(observer.getID())) //check if the observer is already registered
            {
                System.out.println("You are already registered as an observer for this airline");
                return;
            }
        }
        observers.add(observer);
        System.out.println("You have been successfully registered as an observer for this airline");
    }

    //remove an observer from the list of observers
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    //notify all the observers about an update
    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    //returns  a new list of airlines companies from the airport composite
    public List <Airline> getAirlinesCompany()
    {
        List <Airline> airlines = new ArrayList<Airline>();
        for (Airport_Composite airportComponent : airportComponentsList)
        {
            if (airportComponent instanceof Airline)
            {
                airlines.add((Airline) airportComponent);
            }
        }
        return airlines;
    }

    //returns a new list of flights from the airport composite and from all the sub-composites
    public List <Flight> getFlightsFromAirportComposite()
    {
        List <Flight> flights = new ArrayList<>();
        for (Airport_Composite airportComponent : this.airportComponentsList)
        {
            if (airportComponent instanceof Flight)
            {
                flights.add((Flight) airportComponent);
            }
            else
            {
                Airline subAirline = (Airline) airportComponent;
                flights.addAll(subAirline.getFlightsFromAirportComposite());
            }
        }
        return flights;
    }

    //sorts the list of airport components first by airlines and then by flights
    public void sortAirportComponentsList ()
    {
        List <Airport_Composite> sortedList = new ArrayList<Airport_Composite>();
        for (Airport_Composite airportComponent : airportComponentsList)
        {
            if (airportComponent instanceof Airline)
            {
                sortedList.add(airportComponent);
            }
        }
        for (Airport_Composite airportComponent : airportComponentsList)
        {
            if (airportComponent instanceof Flight)
            {
                sortedList.add(airportComponent);
            }
        }
        airportComponentsList = sortedList;
    }


}
