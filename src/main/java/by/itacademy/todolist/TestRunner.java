package by.itacademy.todolist;

import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistence.dao.impl.*;
import by.itacademy.todolist.persistence.util.JpaEntityManagerFactoryUtil;
import org.h2.tools.Server;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

public class TestRunner {

    private static final EntityManagerFactory MANAGER_FACTORY = JpaEntityManagerFactoryUtil.getEntityManagerFactory();
    private static final UserDaoJpa USER_DAO = new UserDaoJpa();
    private static final RoleDaoJpa ROLE_DAO = new RoleDaoJpa();
    private static final TaskDaoJpa TASK_DAO = new TaskDaoJpa();
    private static final ProfileDaoJpa PROFILE_DAO = new ProfileDaoJpa();
    private static final MessageDaoJpa MESSAGE_DAO = new MessageDaoJpa();

    private static final Server SERVER;

    static {
        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            throw new RuntimeException("Failed start tcp H2 server");
        }
    }

    public static void main(String[] args) {
        List<Task> taskList = TASK_DAO.getAllUserTasks(1);
        TASK_DAO.deleteAllUserTasks(1);

        taskList = TASK_DAO.getAllUserTasks(1);


        System.out.println("fff");
//
    //    User user = USER_DAO.getById(1);

   //     user.getProfile().setLogin("new");
     //   PROFILE_DAO.update(user.getProfile());
//
//        Task task = new Task();
//        task.setName("testname");
//        task.setDateAdded(LocalDateTime.now());
//        task.setDateCompletion(LocalDateTime.now());
//        task.setDescription("description");
//        task.setUser(user);
//        TASK_DAO.save(task);
//




         //it work
  //  Role adminWithUsers = ROLE_DAO.getByIdWithUsers(1);


//        User testUser = new User();
//        testUser.setName("testname");
//        testUser.setSurname("surnameTest");
//        testUser.setEmail("emailTest");
//        testUser.addProfile(Profile.builder().login("testlogin1").password("testpassword").isEnable(true).build());
//        USER_DAO.save(testUser);
//        testUser.addRole(adminWithUsers);
//        USER_DAO.update(testUser);

    //    testUser = USER_DAO.getById(testUser.getId());
  //      adminWithUsers = ROLE_DAO.getByIdWithUsers(1);
//        testUser.removeRole(adminWithUsers);
//        ROLE_DAO.update(adminWithUsers);





    }
}
