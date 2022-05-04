package model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "user_id")
    private int idUser;

@Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_name")
    private String userName;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "task_id")
    private Task task;

    public User(String firstName, String lastName, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
    }

    public User() {
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int id) {
        this.idUser= id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", task=" + task +
                '}';
    }

    public void addUser(String firstName, String lastName, String userName){
       Session session = HibernateUtils.createSessionFactory().openSession();
       session.beginTransaction();
       User user = new User(firstName,lastName,userName);
       session.save(user);
       session.getTransaction().commit();
   }

   public void showAllUsers (){
       Session session = HibernateUtils.createSessionFactory().openSession();
       Transaction tx =session.beginTransaction();

       String hql = "from User";
       Query query = session.createQuery(hql);
       List<User> userList = query.list();
       for(User u : userList){
           System.out.println(u);
       }
       tx.commit();
   }
    public void addTask (String taskTitle, String taskDescription) {
        Session session = HibernateUtils.createSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User user = new User( this.firstName,this.lastName,this.userName);
        Task task = new Task(taskTitle,taskDescription);

        user.setTask(task);
        session.persist(user);
        tx.commit();
    }
}
