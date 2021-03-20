package by.itacademy.todolist.listener;

import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;

@WebListener
public class ApplicationStartUpListener implements ServletContextListener {

    private static Server SERVER;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            throw new RuntimeException("Failed start tcp H2 server");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}