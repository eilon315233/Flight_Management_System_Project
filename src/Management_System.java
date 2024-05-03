import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/*
    This class represents the management system of the airport.
 */
public class Management_System {
    //Variables
    private static Management_System instance = null; //Singleton pattern
    private List<User> usersList; //List of users in the system
    private List<Airline> airlinesList; //List of airlines in the system

    //Constructor
    private Management_System() {
        usersList = new ArrayList<User>();
        airlinesList = new ArrayList<Airline>();
    }

    //Methods

    //This method use the Singleton pattern
    public static Management_System getInstance() {
        if (instance == null) {
            instance = new Management_System();
        }
        return instance;
    }

    public List <User> getUsersList() {
        return usersList;
    }

    public List <Airline> getAirlinesList() {
        return airlinesList;
    }

    //This method gets the user by ID from the users list
    public User getUserByID(String ID) {
        for (User user : usersList) {
            if (user.getID().equals(ID)) {
                return user;
            }
        }
        return null;
    }

    //This method add user to the system

    public User addUserToSystem(String ID, String name, String password, Airline airline) {
        for (User user : usersList) {
            if (user.getID().equals(ID)) //check if the user already exists
            {
                System.out.println("User with ID " + ID + " already exists");
                return null;
            }
        }

        //check if the user is a passenger or an airport worker

        if (airline == null) //if the airline is null, then the user is a passenger
        {
            Passenger passenger = new Passenger(ID, name, password);
            usersList.add(passenger);
            System.out.println("Passenger with ID " + passenger.getID() + " has been successfully added");
            return passenger;
        }
        else //if the airline is not null, then the user is an airport worker
        {
            Airport_worker employee = new Airport_worker(ID, name, password, airline);
            usersList.add(employee);
            System.out.println("Airport_worker with ID " + employee.getID() + " has been successfully added");
            return employee;
        }
    }

    //This method get all the flights in the airport
    public List<Flight> getAllFlightsInAirport() {
        Set<Flight> flights = new HashSet<>();
        for (Airline airline : this.getAirlinesList()) {
            flights.addAll(airline.getFlightsFromAirportComposite());
        }
        return new ArrayList<>(flights); //return the flights as a list
    }


    //This method gets the airline by name from the airlines list by searching all the airlines and their sub airlines recursively
    public Airline getAirlineFromAirlinesList(String airlineName) {return  getAirlineFromAirlinesListRec(airlineName, airlinesList);}


    //This method gets the airline by name from the airlines list recursively
    public Airline getAirlineFromAirlinesListRec(String airlineName, List<Airline> airlinesList) {
        for (Airline airline : airlinesList) {
            if (airline.getName().equals(airlineName)) {
                return airline;
            }
            Airline checkAirline = getAirlineFromAirlinesListRec(airlineName, airline.getAirlinesCompany());
            if (checkAirline != null) {
                return checkAirline;
                }
            }
        return null;
    }

}




