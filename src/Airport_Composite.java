/* This interface is used to implement the Composite design pattern.
   It has three methods: add, remove, and display.
   The add method is used to add an airport component to the Airport_Composite.
   The remove method is used to remove an airport component from the Airport_Composite.
   The display method is used to display the airport component.
 */
public interface Airport_Composite {
    void add(Airport_Composite airportComponent); //add airport component to the Airport_Composite
    void remove(Airport_Composite airportComponent); //remove airport component from the Airport_Composite
    void display(); //display the airport component
}
