package by.itacademy.todolist;

import by.itacademy.todolist.persistance.util.JpaEntityManagerFactoryUtil;
import org.h2.tools.Server;

import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;

public class TestRunner {

    private static final EntityManagerFactory MANAGER_FACTORY = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    private static final Server SERVER;

    static {
        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            throw new RuntimeException("Failed start tcp H2 server");
        }
    }

    public static void main(String[] args) {
        System.out.println("successful");
    }
}
