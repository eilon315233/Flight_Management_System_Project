import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
    This class represents the main class of the management system.
 */

public class Main {
    public static void main(String[] args) {
        Management_System management_system = Management_System.getInstance(); //Singleton pattern
        EnterAirlinesCompanies(management_system); //Adding airlines companies to the system
        startSystem(management_system); //Starting the system
    }

    //This method adds airlines companies and sub-companies to the system (by default)
    public static void EnterAirlinesCompanies(Management_System management_system)
    {
        //Creating airlines companies and sub-companies
        Airline airline_El_Al = new Airline("El_Al");
        Airline airline_Turkish_airlines = new Airline("Turkish_airlines");
        Airline airline_United_airlines = new Airline("United_airlines");

        Airline airline_El_Al_b = new Airline("El_Al_business");
        Airline airline_Turkish_airlines_b = new Airline("Turkish_airlines_business");
        Airline airline_United_airlines_b = new Airline("United_airlines_business");

        //Adding the sub-companies to the Big companies airlines
        airline_El_Al.add(airline_El_Al_b);
        airline_Turkish_airlines.add(airline_Turkish_airlines_b);
        airline_United_airlines.add(airline_United_airlines_b);

        //Adding the airlines company and sub-companies to the system
        management_system.getAirlinesList().add(airline_El_Al);
        management_system.getAirlinesList().add(airline_Turkish_airlines);
        management_system.getAirlinesList().add(airline_United_airlines);
    }


    //This method starts the system
    public static void startSystem(Management_System management_system)
    {
        System.out.println("-----------------------------------------------");
        System.out.println("Welcome to the Flight Management System!");
        System.out.println("Please select an option:");
        System.out.println("1. Login as an existing user");
        System.out.println("2. Register as a new user");
        System.out.println("3. To exit the system");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();

        //Switch case for the options
        switch (option)
        {
            //To exit the system
            case "3":
                System.out.println("Thank you for using the Flight Management System, goodbye!");
                return;

            //Login as an existing user
            case "1":
                System.out.println("Enter your ID:");
                String ID = scanner.next();
                if (management_system.getUserByID(ID) == null) //check if the user exists
                {
                    System.out.println("User with ID " + ID + " does not exist, please try again");
                    startSystem(management_system);
                }
                System.out.println("Enter your password:");
                String password = scanner.next();
                if (management_system.getUserByID(ID).getPassword().equals(password)) //check if the password is correct
                {
                    if (management_system.getUserByID(ID) instanceof Passenger) //if the user is a passenger
                    {
                        Passenger passenger = (Passenger) management_system.getUserByID(ID);
                        PassengerMenu(passenger, management_system); //call the passenger menu
                    }
                    else if (management_system.getUserByID(ID) instanceof Airport_worker) //if the user is an airport worker
                    {
                        Airport_worker airportWorker = (Airport_worker) management_system.getUserByID(ID);
                        AirportWorkerMenu(airportWorker, management_system); //call the airport worker menu
                    }
                }
                else //if the password is incorrect
                {
                    System.out.println("The password is incorrect, please try again");
                    startSystem(management_system);
                }
                return;

            //Register as a new user
            case "2":
                System.out.println("Enter your username:");
                String username = scanner.next();
                System.out.println("Enter your password:");
                String pass = scanner.next();
                System.out.println("Enter your ID:");
                String id = scanner.next();
                for (User user : management_system.getUsersList()) //check if the user already exists
                {
                    if (user.getID().equals((id)))
                    {
                        System.out.println("User with ID " + id + " already exists");
                        startSystem(management_system);
                    }
                }
                //check if the user is a passenger or an airport worker
                System.out.println("Are you a passenger or an airport worker(p/a)?");
                String type = scanner.next();

                if (type.equals("p")) //if the user is a passenger
                {
                    //add the passenger to the system
                    Passenger passenger = (Passenger) management_system.addUserToSystem(id, username, pass, null);
                    PassengerMenu(passenger, management_system); //call the passenger menu
                }
                else if (type.equals("a")) //if the user is an airport worker
                    {
                        System.out.println("Enter the name of the airline you work for:");
                        System.out.println(" ");

                        //all the options of the airlines companies and sub-companies
                        System.out.println("Your options are:");
                        System.out.println("Big companies(El_Al, Turkish_airlines, United_airlines)");
                        System.out.println("sub-companies(El_Al_business, Turkish_airlines_business, United_airlines_business)");


                        String airlineName = scanner.next();

                        //check if the airline exists
                        Airline airline = management_system.getAirlineFromAirlinesList(airlineName);
                        if (airline == null) {
                            System.out.println("Airline with name " + airlineName + " does not exist, please try again");
                            startSystem(management_system);
                        }
                        //add the airport worker to the system
                        Airport_worker airportWorker = (Airport_worker) management_system.addUserToSystem(id,username,pass,airline);
                        AirportWorkerMenu(airportWorker, management_system);//call the airport worker menu
                    }
                //if the user entered an invalid input
                else {
                    System.out.println("Invalid input, please try again");
                    startSystem(management_system);
                }
        }
    }

    //This method is the menu of the passenger
    public static void PassengerMenu(Passenger passenger, Management_System management_system)
    {
        System.out.println("-----------------------------------------------");
        System.out.println("Welcome " + passenger.getName() + "!");
        System.out.println("Please select an option:");
        System.out.println("1. Register to a flight");
        System.out.println("2. Register as an observer to a Airline");
        System.out.println("3. Search for a flight");
        System.out.println("4. Show all my flights");
        System.out.println("9. Return to the main menu");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();

        //Switch case for the options
        switch (option) {
            //Return to the main menu
            case "9":
                startSystem(management_system);
                return;

            //Register to a flight
            case "1":
                System.out.println("Enter the ID of the flight you want to register to:");
                String flightID = scanner.next();

                //check if the flight exists
                Flight flight = null;
                for (Airline airline : management_system.getAirlinesList()) {
                    for (Flight temp : airline.getFlightsFromAirportComposite()) {
                        if (temp.getFlightID().equals(flightID)) {
                            flight = temp;
                            break;
                        }
                    }
                }
                //if the flight does not exist
                if (flight == null) {
                    System.out.println("Flight with ID " + flightID + " does not exist, please try again");
                    PassengerMenu(passenger, management_system);
                }
                //the flight exists and register the passenger to the flight
                passenger.registerToFlight(flight);

                //ask the passenger if he wants to register as an observer to the flight
                System.out.println("Would you like to register as a observer to this flight(y/n)?");
                String answer = scanner.next();
                if (answer.equals("y")) {
                    flight.addObserver(passenger);
                    System.out.println("You have been successfully registered as an observer to flight with ID " + flightID);
                    PassengerMenu(passenger, management_system);
                }
                return;

            //Register as an observer to a Airline
            case "2":
                System.out.println("Enter the name of the airline you want to register as an observer to:");
                String airlineName = scanner.next();
                //check if the airline exists
                Airline airline = management_system.getAirlineFromAirlinesList(airlineName);
                if (airline == null) {
                    System.out.println("Airline with name " + airlineName + " does not exist, please try again");
                    PassengerMenu(passenger, management_system);
                }
                //register the passenger as an observer to the airline
                airline.addObserver(passenger);
                PassengerMenu(passenger, management_system);
                return;

            //Search for a flight
            case "3":
                System.out.println("Please select a type of search:");
                System.out.println("1. Search by flight ID");
                System.out.println("2. Search by duration of flight");
                System.out.println("3. Search by departure time");
                System.out.println("4. Search by price");
                System.out.println("9. Return to the Passenger menu");

                String searchOption = scanner.next();
                //search context for the search strategies using the strategy pattern
                SearchContext searchContext = new SearchContext(new FlightID_Search_Strategy());

                //switch case for the search options
                switch (searchOption) {

                    //Search by flight ID
                    case "1":
                        System.out.println("Enter the ID of the flight you want to search for:");
                        String ID = scanner.next();
                        searchContext.TypeOfSearch(management_system.getAllFlightsInAirport(), ID);
                        PassengerMenu(passenger, management_system);
                        return;

                    //Search by duration of flight
                    case "2":
                        System.out.println("Enter the duration of the flight you want to search for:");
                        String duration = scanner.next();
                        searchContext.setSearchStrategy(new DurationFlight_Search_Strategy());
                        searchContext.TypeOfSearch(management_system.getAllFlightsInAirport(), duration);
                        PassengerMenu(passenger, management_system);
                        return;

                    //Search by departure time
                    case "3":
                        System.out.println("Enter the departure time of the flight you want to search for:");
                        String departureTime = scanner.next();
                        searchContext.setSearchStrategy(new DepartureTime_Search_Strategy());
                        searchContext.TypeOfSearch(management_system.getAllFlightsInAirport(), departureTime);
                        PassengerMenu(passenger, management_system);
                        return;

                    //Search by price
                    case "4":
                        System.out.println("Enter the price of the flight you want to search for:");
                        String price = scanner.next();
                        searchContext.setSearchStrategy(new Price_Search_Strategy());
                        searchContext.TypeOfSearch(management_system.getAllFlightsInAirport(), price);
                        PassengerMenu(passenger, management_system);
                        return;

                     //Return to the Passenger menu
                    case "9":
                        PassengerMenu(passenger, management_system);
                        return;

                    //If the user entered an invalid input
                    default:
                        System.out.println("Invalid input, please try again");
                        PassengerMenu(passenger, management_system);
                        return;
                }

            //Show all the flights of the passenger
            case "4":
                System.out.println("All your flights:");
                for (Flight temp : passenger.getFlights()) {
                    System.out.println("Flight ID: " + temp.getFlightID() + " Duration: " + temp.getDurationFlight() + " Departure Time: " + temp.getDepartureTime() + " Price: " + temp.getPrice() + "$");
                }
                PassengerMenu(passenger, management_system);
                return;

            //If the user entered an invalid input
            default:
                System.out.println("Invalid input, please try again");
                PassengerMenu(passenger, management_system);
                return;
        }
    }

    //This method is the menu of the airport worker
    public static void AirportWorkerMenu(Airport_worker airportWorker, Management_System management_system)
    {
        System.out.println("-----------------------------------------------");
        System.out.println("Welcome " + airportWorker.getName() + "!");
        System.out.println("Please select an option:");
        System.out.println("1. Add a flight To the airport");
        System.out.println("2. Show all the flights and airlines in your company");
        System.out.println("3. Change details of a flight that exists in your airline");
        System.out.println("4. Search for a flight in the airport");
        System.out.println("5. Change the availability of a flight");
        System.out.println("6. Register a new observer to a Airline");
        System.out.println("7. Show all the passengers in a flight");
        System.out.println("8. Notify all observers of a airline about a new deal");
        System.out.println("9. Return to the main menu");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();

        //Switch case for the options
        switch (option)
        {
            //Return to the main menu
            case "9":
                startSystem(management_system);
                return;

            //Add a flight To the airport
            case "1":
                System.out.println("Enter the ID of the flight you want to add:");
                String flightID = scanner.next();

                //check if the flight already exists
                if (management_system.getAllFlightsInAirport().contains(flightID))
                {
                    System.out.println("Flight with ID " + flightID + " already exists, please try again");
                    AirportWorkerMenu(airportWorker, management_system);
                }

                // get the details of the flight
                System.out.println("Enter the departure time of the flight:");
                String departureTime = scanner.next();
                System.out.println("Enter the duration of the flight:");
                String duration = scanner.next();
                System.out.println("Enter the price of the flight:");
                String price = scanner.next();

                //add the flight to the airport worker company
                airportWorker.getCompany().addFlightToAirportComposite(flightID, duration, departureTime, Double.parseDouble(price));
                System.out.println("Flight with ID " + flightID + " has been successfully added");
                AirportWorkerMenu(airportWorker, management_system);
                return;

            //Show all the flights and airlines in the company
            case "2":
                System.out.println("All the flights and airlines in your company:");
                Airport_worker.getCompany().display();
                AirportWorkerMenu(airportWorker, management_system);
                return;

            //Change details of a flight that exists in your airline
            case "3":
                System.out.println("Please enter the flight ID:");
                String flight_ID = scanner.next();

                //check if the flight exists
                Flight flightEdit = null;
                for (Airport_Composite component : Airport_worker.getCompany().getFlightsFromAirportComposite()) {
                    Flight temp = (Flight) component;
                    if (temp.getFlightID().equals(flight_ID)) {
                        flightEdit = temp;
                        break;
                    }
                }
                //if the flight does not exist
                if (flightEdit == null) {
                    System.out.println("Flight " + flight_ID + " does not exist in your airline (including sub-companies)");
                    AirportWorkerMenu(airportWorker, management_system);
                    return;
                }

                //get the details the airport worker wants to change
                System.out.println("Please select the detail you want to change:");
                System.out.println("1. Change the departure time");
                System.out.println("2. Change the duration of the flight");
                System.out.println("3. Change the price of the flight");
                System.out.println("9. Return to the Airport Worker menu");

                String editOption = scanner.next();

                //switch case for the edit options
                switch (editOption)
                {
                    //Change the departure time and notify the observers
                    case "1":
                        System.out.println("Enter the new departure time:");
                        String newDepartureTime = scanner.next();
                        flightEdit.setDepartureTime(newDepartureTime);
                        System.out.println("The departure time of flight " + flight_ID + " has been successfully changed to " + newDepartureTime);
                        flightEdit.notifyObservers("The departure time of the flight has been changed to " + newDepartureTime);
                        AirportWorkerMenu(airportWorker, management_system);
                        return;

                    //Change the duration of the flight and notify the observers
                    case "2":
                        System.out.println("Enter the new duration of the flight:");
                        String newDuration = scanner.next();
                        flightEdit.setDurationFlight(newDuration);
                        System.out.println("The duration of flight " + flight_ID + " has been successfully changed to " + newDuration);
                        flightEdit.notifyObservers("The duration of the flight has been changed to " + newDuration);
                        AirportWorkerMenu(airportWorker, management_system);
                        return;

                    //Change the price of the flight and notify the observers
                    case "3":
                        System.out.println("Enter the new price of the flight:");
                        String newPrice = scanner.next();
                        flightEdit.setPrice(Double.parseDouble(newPrice));
                        System.out.println("The price of flight " + flight_ID + " has been successfully changed to " + newPrice);
                        flightEdit.notifyObservers("The price of the flight has been changed to " + newPrice);
                        AirportWorkerMenu(airportWorker, management_system);
                        return;

                    //Return to the Airport Worker menu
                    case "9":
                        AirportWorkerMenu(airportWorker, management_system);
                        return;

                    //If the user entered an invalid input
                    default:
                        System.out.println("Invalid input, please try again");
                        AirportWorkerMenu(airportWorker, management_system);
                        return;
                }

            //Change the availability of a flight
            case "5":
                System.out.println("Please enter the flight ID:");
                String flightIDChange = scanner.next();

                //check if the flight exists
                Flight flightChange = null;
                for (Airport_Composite component : Airport_worker.getCompany().getFlightsFromAirportComposite()) {
                    Flight temp = (Flight) component;
                    if (temp.getFlightID().equals(flightIDChange)) {
                        flightChange = temp;
                        break;
                    }
                }

                //if the flight does not exist
                if (flightChange == null) {
                    System.out.println("Flight " + flightIDChange + " does not exist in your airline (including sub-companies)");
                    AirportWorkerMenu(airportWorker, management_system);
                    return;
                }

                //change the availability of the flight
                String available = flightChange.isAvailable() ? "available" : "not available";
                System.out.println("The availability of flight " + flightIDChange + " is " + available);
                System.out.println("Do you want to change the availability of the flight(y/n)?");
                String answer = scanner.next();

                //the airport worker wants to change the availability
                if (answer.equals("y"))
                {
                    flightChange.setAvailable(!flightChange.isAvailable());
                    available = flightChange.isAvailable() ? "available" : "not available";
                    System.out.println("The availability of flight " + flightIDChange + " has been successfully changed to " + available);
                    flightChange.notifyObservers("The availability of the flight has been changed to " + available);
                    AirportWorkerMenu(airportWorker, management_system);
                    return;
                }

                //the airport worker does not want to change the availability
                else if (answer.equals("n"))
                {
                    System.out.println("The availability of flight " + flightIDChange + " has not been changed");
                    AirportWorkerMenu(airportWorker, management_system);
                    return;
                }

                //If the user entered an invalid input
                else
                {
                    System.out.println("Invalid input, please try again");
                    AirportWorkerMenu(airportWorker, management_system);
                    return;
                }

            //Search for a flight in the airport
            case "4":
                System.out.println("Please select a type of search:");
                System.out.println("1. Search by flight ID");
                System.out.println("2. Search by duration of flight");
                System.out.println("3. Search by departure time");
                System.out.println("4. Search by price");
                System.out.println("9. Return to the Airport Worker menu");

                String searchOption = scanner.next();

                //search context for the search strategies using the strategy pattern
                SearchContext searchContext = new SearchContext(new FlightID_Search_Strategy());

                //switch case for the search options
                switch (searchOption)
                {
                    //Search by flight ID
                    case "1":
                        System.out.println("Enter the ID of the flight you want to search for:");
                        String ID = scanner.next();
                        searchContext.TypeOfSearch(Airport_worker.getCompany().getFlightsFromAirportComposite(), ID);
                        AirportWorkerMenu(airportWorker, management_system);
                        return;

                    //Search by duration of flight
                    case "2":
                        System.out.println("Enter the duration of the flight you want to search for:");
                        String duration_1 = scanner.next();
                        searchContext.setSearchStrategy(new DurationFlight_Search_Strategy());
                        searchContext.TypeOfSearch(Airport_worker.getCompany().getFlightsFromAirportComposite(), duration_1);
                        AirportWorkerMenu(airportWorker, management_system);
                        return;

                    //Search by departure time
                    case "3":
                        System.out.println("Enter the departure time of the flight you want to search for:");
                        String departureTime_1 = scanner.next();
                        searchContext.setSearchStrategy(new DepartureTime_Search_Strategy());
                        searchContext.TypeOfSearch(Airport_worker.getCompany().getFlightsFromAirportComposite(), departureTime_1);
                        AirportWorkerMenu(airportWorker, management_system);
                        return;
                    //Search by price
                    case "4":
                        System.out.println("Enter the price of the flight you want to search for:");
                        String price_1 = scanner.next();
                        searchContext.setSearchStrategy(new Price_Search_Strategy());
                        searchContext.TypeOfSearch(Airport_worker.getCompany().getFlightsFromAirportComposite(), price_1);
                        AirportWorkerMenu(airportWorker, management_system);
                        return;

                    //Return to the Airport Worker menu
                    case "9":
                        AirportWorkerMenu(airportWorker, management_system);
                        return;

                    //If the user entered an invalid input
                    default:
                        System.out.println("Invalid input, please try again");
                        AirportWorkerMenu(airportWorker, management_system);
                        return;
                }

            //Register a new observer to a Airline
            case "6":
                System.out.println("Enter the name of the airline you want to register a new observer to:");
                String airlineName = scanner.next();

                //check if the airline exists
                Airline airline = management_system.getAirlineFromAirlinesList(airlineName);
                if (airline == null) {
                    System.out.println("Airline with name " + airlineName + " does not exist, please try again");
                    AirportWorkerMenu(airportWorker, management_system);
                }

                //register the airport worker as an observer to the airline
                airline.addObserver(airportWorker);
                AirportWorkerMenu(airportWorker, management_system);
                return;

             //Show all the passengers in a flight
            case "7":
                System.out.println("Please enter the flight ID:");
                String flightIDPassengers = scanner.next();

                //check if the flight exists
                Flight flightPassengers = null;
                for (Airport_Composite component : Airport_worker.getCompany().getFlightsFromAirportComposite()) {
                    Flight temp = (Flight) component;
                    if (temp.getFlightID().equals(flightIDPassengers)) {
                        flightPassengers = temp;
                        break;
                    }
                }

                //if the flight does not exist
                if (flightPassengers == null) {
                    System.out.println("Flight " + flightIDPassengers + " does not exist in your airline (including sub-companies)");
                    AirportWorkerMenu(airportWorker, management_system);
                    return;
                }

                //show all the passengers in the flight
                System.out.println("Passengers in flight " + flightIDPassengers + ":");
                for (Passenger passenger : flightPassengers.getPassengersList()) {
                    System.out.println("Passenger ID: " + passenger.getID() + " Name: " + passenger.getName());
                }
                AirportWorkerMenu(airportWorker, management_system);
                return;

            //Notify all observers of a airline about a new deal
            case "8":
                System.out.println("Please enter the message you want to send to all observers:");
                String message = scanner.next();
                Airport_worker.getCompany().notifyObservers(message);
                System.out.println("All observers have been notified");
                AirportWorkerMenu(airportWorker, management_system);
                return;

            //If the user entered an invalid input
            default:
                System.out.println("Invalid input, please try again");
                AirportWorkerMenu(airportWorker, management_system);
                return;
        }
    }
}







