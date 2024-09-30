# Flight Management System

This project is a **Flight Management System** designed for airport workers to manage flights within an airline company. It provides functionalities such as adding flights, searching for flights, editing flight details, managing availability, and notifying observers when flight details are changed.

## Features

- **Add Flight**: Allows an airport worker to add new flights to the company's system.
- **View All Flights and Airlines**: Displays all flights and airlines associated with the airport worker’s company.
- **Edit Flight Details**: Provides options to modify existing flight information such as:
  - Departure time
  - Flight duration
  - Flight price
- **Change Flight Availability**: Allows toggling the availability of flights in the system.
- **Search Flights**: Enables searching for flights by various criteria:
  - Flight ID
  - Flight duration
  - Departure time
  - Price
- **Observer Pattern**: Uses the Observer design pattern to notify relevant parties when flight details change (e.g., change in availability, price, or schedule).
- **Passenger Management**: Shows all passengers registered on a flight.
- **Airline Notifications**: Allows the airport worker to send messages to all registered observers of an airline.

## System Components

### Main Classes:

1. **Airline.java**: Represents an airline within the company, handling flight information and observer management.
2. **Airport_Composite.java**: Part of the composite pattern used to manage flights and sub-companies.
3. **Airport_worker.java**: Represents the airport worker who manages flights, edits details, and interacts with the flight management system.
4. **DepartureTime_Search_Strategy.java**: Implements a search strategy based on flight departure times.
5. **DurationFlight_Search_Strategy.java**: Implements a search strategy based on flight duration.
6. **Flight.java**: Represents a flight within the system. It has properties such as flight ID, departure time, duration, and price, with methods to notify observers about changes.
7. **FlightID_Search_Strategy.java**: Implements a search strategy based on flight IDs.
8. **Main.java**: The main class that runs the flight management system and handles user input through a menu.
9. **Management_System.java**: Provides core management functions such as adding flights and managing observers.
10. **Observable.java**: Defines the observable part of the Observer pattern, allowing registration and notification of observers.
11. **Observer.java**: Defines an observer that gets notified about changes in flight details or airline information.
12. **Passenger.java**: Represents a passenger who is registered for a flight within the system.
13. **Price_Search_Strategy.java**: Implements a search strategy based on flight prices.
14. **SearchContext.java**: Manages the different search strategies, enabling flexible flight searching based on criteria.
15. **User.java**: Represents the user interacting with the flight management system, typically the airport worker.
16. **searchStrategy.java**: An interface that defines the general search strategy for finding flights.

### Design Patterns Used:

- **Observer Pattern**: Used to notify observers (airport workers) about changes in flight information.
- **Composite Pattern**: Used to manage flights and sub-companies.
- **Strategy Pattern**: Implements various search strategies for flights based on different criteria.

## Usage

### Menu Options:
1. **Add a Flight**: Enter the flight ID, duration, departure time, and price to add a new flight to the system.
2. **View All Flights**: Lists all flights and their details.
3. **Edit Flight**: Allows editing flight details such as duration, departure time, and price.
4. **Search for Flights**: Search for flights by ID, duration, departure time, or price.
5. **Change Flight Availability**: Allows changing whether a flight is available or not.
6. **Register an Observer**: Allows registering an observer to an airline for notifications.
7. **Show Passengers**: Displays the list of passengers on a flight.
8. **Notify Observers**: Sends a notification to all observers of an airline with a custom message.

## How to Run

1. Clone the repository.
2. Compile the Java source files.
3. Run the `main()` function in `Main.java` to start the system.
4. Follow the menu prompts to interact with the flight management system.

## Future Improvements

- **Passenger Booking**: Add functionality for passengers to book flights.
- **Flight Cancellation**: Introduce the ability to cancel flights and notify passengers.
- **Additional Search Criteria**: Add more advanced search options, such as filtering by date or destination.
