import java.util.ArrayList;
import java.util.List;

/*
     This class represents a passenger in the system.
     It extends the User class and implements the Observer interface.
 */
public class Passenger extends User implements Observer
{
    //Variables
    private List<Flight> flightsList; //List of flights that the passenger is registered to

    //constructor
    public Passenger(String name, String ID, String password)
    {
        super(name, ID, password);
        this.flightsList = new ArrayList<Flight>();
    }

    //methods

    //Observer pattern implementation

    //update method that is called when the observer receives a message
    @Override
    public void update(String message) {
        System.out.println("Passenger " + getName() + " received message: " + message);
    }

    //getters, used to get the name and ID of the passenger as observer
    @Override
    public String getID() {
        return super.getID();
    }
    @Override
    public String getName() {
        return super.getName();
    }


    //method that registers the passenger to a flight
    public void registerToFlight(Flight flight)
    {
        if (flightsList.contains(flight)) //if the passenger is already registered to the flight
        {
            System.out.println("Passenger " + getName() + " is already registered to flight with ID " + flight.getFlightID());
            return;
        }

        //if the flight is not available
        if (!flight.isAvailable())
        {
            System.out.println("Flight with ID " + flight.getFlightID() + " is not available");
            return;
        }

        flight.addPassengerToFlight(this); //add the passenger to the flight
        flightsList.add(flight);//add the flight to the list of flights that the passenger is registered to
        System.out.println("Passenger " + getName() + " has been successfully registered to flight with ID " + flight.getFlightID());
    }


    // this method is used to get the list of flights that the passenger is registered to
    public List<Flight> getFlights()
    {
        return flightsList;
    }


}
