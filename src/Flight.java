import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
   This class represents a flight that can contain passengers.
   It implements the Airport_Composite interface.
   It also implements the Observable interface to notify the observers about updates.
*/


public class Flight implements Airport_Composite, Observable{

    //variables
    private String flightID;
    private String durationFlight; //duration of the flight, like: "01:30"
    private String departureTime; //departure time of the flight, like: "12:30"
    private double price; //price of the flight
    private List<Passenger> passengersList; //list of passengers registered to the flight
    private final HashSet<Observer> observers = new HashSet<Observer>(); //set of observers to notify
    private boolean isAvailable ; //availability of the flight

    //constructor
    public Flight(String flightID, String durationFlight, String departureTime, double price)
    {
        this.flightID = flightID;
        this.durationFlight = durationFlight;
        this.departureTime = departureTime;
        this.price = price;
        this.passengersList = new ArrayList<Passenger>();
        this.isAvailable = true;
    }

    //methods
    public String getFlightID() {return flightID;}
    public String getDurationFlight() {return durationFlight;}
    public String getDepartureTime() {return departureTime;}
    public double getPrice() {return price;}
    public List<Passenger> getPassengersList() {return passengersList;}

    public boolean isAvailable() {return isAvailable;}

    //setters
    public void setDurationFlight(String durationFlight) {this.durationFlight = durationFlight;}
    public void setDepartureTime(String departureTime) {this.departureTime = departureTime;}
    public void setPrice(double price) {this.price = price;}
    public void setAvailable(boolean available) {isAvailable = available;}

    //add a passenger to the flight
    public void addPassengerToFlight (Passenger passenger)
    {
        for (Passenger p : passengersList)
        {
            if (p.getID().equals(passenger.getID())) //check if the passenger is already registered
            {
                System.out.println("Passenger " + passenger.getName() + " is already registered to this flight");
                return;
            }
        }
        passengersList.add(passenger);
    }


    // Composite pattern implementation

    //add a component to the flight
    @Override
    public void add(Airport_Composite airportComponent) {
        throw new UnsupportedOperationException("Flight does not support add operation");
    }

    //remove a component from the flight
    @Override
    public void remove(Airport_Composite airportComponent) {
        throw new UnsupportedOperationException("Flight does not support remove operation");
    }

    //display the flight
    @Override
    public void display() {
        System.out.println("Flight: ID: " + flightID + ", Duration: " + durationFlight + ", Departure time: " + departureTime + ", Price: " + price + "$, Available: " + isAvailable);
    }

    // Observer pattern implementation

    //add an observer to the list of observers
    @Override
    public void addObserver(Observer observer) { observers.add(observer);}

    //remove an observer from the list of observers
    @Override
    public void removeObserver(Observer observer) { observers.remove(observer);}

    //notify the observers about updates
    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
