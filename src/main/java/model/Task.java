package model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
public class Task{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id_task")
    private int id;
    @Column(name = "task_title")
    private String taskTitle;
    @Column(name = "task_description")
    private String taskDescription;

    @OneToMany(mappedBy = "tasks")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<User> users;


    public Task(String taskTitle, String taskDescription) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }


    public Task() {
    }

    public Task(String taskTitle, String taskDescription, int idUser) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }



    public void addTaskToGroup ( String taskTitle, String taskDescription){
      Session session = HibernateUtils.createSessionFactory().openSession();
      session.beginTransaction();
        User user= new User();
        Task task = new Task(taskTitle,taskDescription);
        user.setTask(task);
        Set<User> userSet = new HashSet<>();
        userSet.add(user);
        task.setUsers(userSet);
        session.persist(task);
        session.save(task);
      session.getTransaction().commit();


  }

}
