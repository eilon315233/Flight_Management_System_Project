/*
     This class is used to create a User object.
        It is used to create a user object like a passenger or an airport worker.
 */
public class User
{
    //variables
    private String  name;
    private String ID;
    private String password;

    //constructor
    public User(String name, String ID, String password)
    {
        this.name = name;
        this.ID = ID;
        this.password = password;
    }

    //methods
    public String getName() {return name;}
    public String getID() {return ID;}
    public String getPassword() {return password;}
    public boolean checkPassword(String password) {return this.password == password;} //check if the password is correct
}
