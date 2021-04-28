package by.itacademy.todolist.listener;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Role;
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
        sce.getServletContext().setAttribute(ApplicationConstants.ROLE_ADMIN_KEY,
                new Role(ApplicationConstants.ROLE_ADMIN_VALUE));
        sce.getServletContext().setAttribute(ApplicationConstants.ROLE_USER_KEY,
                new Role(ApplicationConstants.ROLE_USER_VALUE));

        try {
            SERVER = Server.createTcpServer().start();
            System.out.println("server TCP was started $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        } catch (SQLException e) {
            throw new RuntimeException("Failed start tcp H2 server");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}