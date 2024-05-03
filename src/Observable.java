/*
    This interface is used to implement the Observer pattern.
    used to update the observers about a change in the subject.
    implemented by Airline class and Flight class.
 */
public interface Observable {
    void addObserver(Observer observer); //add an observer to the list of observers
    void removeObserver(Observer observer); //remove an observer from the list of observers
    void notifyObservers(String message);//notify all the observers about an update
}
