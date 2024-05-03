/*
    This is a class that represents an airport worker on airline company.
    It extends the User class and implements the Observer interface.
 */

public class Airport_worker extends User implements Observer
{
    //variables
    private static Airline company;

    //constructor
    public Airport_worker(String name, String ID, String password, Airline company)
    {
        super(name, ID, password);
        this.company = company;
    }


    //methods

    //Observer pattern implementation

    //update method that is called when the observer receives a message
    @Override
    public void update(String message) {
        System.out.println("Airport worker " + getName() + " received message: " + message);
    }

    //getters, used to get the name and ID of the airport worker as observer
    @Override
    public String getID() {
        return super.getID();
    }
    @Override
    public String getName() {
        return super.getName();
    }

    public static Airline getCompany() {return company;}

}
