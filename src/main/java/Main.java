import model.Task;
import model.User;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        //add new user
        User user = new User();
        user.addUser("Qa","Evrica","Ex51");

        // show all users
       user.showAllUsers();

        //Add a task to the user
        user.addTask("do somethink","somethink");


    }
}
