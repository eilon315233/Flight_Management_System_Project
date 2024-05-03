/*
    This interface is used to implement the Observer pattern.
    used to update the observers about a change in the subject.
    implemented by Airport_worker class and Passenger class.
 */
public interface Observer {
    void update(String message);

    Object getID();
    String getName();

}
