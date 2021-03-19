package by.itacademy.todolist;

import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;
import by.itacademy.todolist.persistence.dao.impl.ProfileDaoJpa;
import by.itacademy.todolist.persistence.dao.impl.RoleDaoJpa;
import by.itacademy.todolist.persistence.dao.impl.TaskDaoJpa;
import by.itacademy.todolist.persistence.dao.impl.UserDaoJpa;
import by.itacademy.todolist.persistence.util.JpaEntityManagerFactoryUtil;
import org.h2.tools.Server;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TestRunner {

    private static final EntityManagerFactory MANAGER_FACTORY = JpaEntityManagerFactoryUtil.getEntityManagerFactory();
    private static final UserDaoJpa USER_DAO = new UserDaoJpa();
    private static final RoleDaoJpa ROLE_DAO = new RoleDaoJpa();
    private static final TaskDaoJpa TASK_DAO = new TaskDaoJpa();
    private static final ProfileDaoJpa PROFILE_DAO = new ProfileDaoJpa();

    private static final Server SERVER;

    static {
        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            throw new RuntimeException("Failed start tcp H2 server");
        }
    }

    public static void main(String[] args) {

        User user = USER_DAO.getById(1);

        Task task = new Task();
        task.setName("testname");
        task.setDateAdded(LocalDateTime.now());
        task.setDateCompletion(LocalDateTime.now());
        task.setDescription("description");
        task.setUser(user);
        TASK_DAO.save(task);

        TASK_DAO.deleteById(task.getId());



        // it work
//        Role adminWithUsers = ROLE_DAO.getByIdWithUsers(1);
//        User testUser = new User();
//        testUser.setName("testname");
//        testUser.setSurname("surnameTest");
//        testUser.setEmail("emailTest");
//        testUser.addProfile(Profile.builder().login("testlogin1").password("testpassword").isEnable(true).build());
//        USER_DAO.save(testUser);
//        testUser.addRole(adminWithUsers);
//         USER_DAO.update(testUser);
//
//        adminWithUsers = ROLE_DAO.getByIdWithUsers(1);
//        testUser.removeRole(adminWithUsers);
//        ROLE_DAO.update(adminWithUsers);





    }
}
