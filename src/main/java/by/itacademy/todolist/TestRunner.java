package by.itacademy.todolist;

import by.itacademy.todolist.model.*;
import by.itacademy.todolist.persistance.connector.Connector;
import by.itacademy.todolist.persistance.connector.impl.HikariConnector;
import by.itacademy.todolist.persistance.dao.*;
import by.itacademy.todolist.persistance.dao.impl.*;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TestRunner {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/d:/database/todolist-test
    private static final Server SERVER;

    static {
        try {
            SERVER = Server.createTcpServer().start();
       } catch (SQLException e) {
            throw new RuntimeException("Failed start tcp H2 server");
        }

    }

    public static void main(String[] args) {
        Connector connector = HikariConnector.getInstance();
        ProfileDao<Profile> profileDao = new ProfileJdbcDao(connector);
        RoleDao<Role> roleDao = new RoleJdbcDao(connector);
        FileInfoDao<FileInfo> fileInfoDao = new FileInfoJdbcDao(connector);
        TaskDao<Task> taskDao = new TaskJdbcDao(connector);
        UserDao<User> userDao = new UserJdbcDao(connector, profileDao, roleDao, taskDao);

        MessageDao<Message> messageDao = new MessageJdbcDao(connector);


//        User user = userDao.getById(4);
//
//        Message message = new Message();
//        message.setDateAdded(LocalDateTime.now());
//        message.setMessage("testmessage");
//        message.setUser(user);
//
//        message = messageDao.create(message);
//        System.out.println(message);

       // System.out.println("------------------------------");


        //messageDao.getAll().forEach(System.out::println);

        Message message = messageDao.getById(1);
        System.out.println(message);


        messageDao.getAll().forEach(System.out::println);

        messageDao.delete(message.getId());

        System.out.println("-------------------------");

        messageDao.getAll().forEach(System.out::println);
        ////jdbc:h2:d:/database/test2


       //// jdbcUrl=jdbc:h2:mem:database;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=TRUE;



    }
}
