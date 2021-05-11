package by.itacademy.todolist.listener;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Role;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationStartUpListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute(ApplicationConstants.ROLE_ADMIN_KEY,
                new Role(ApplicationConstants.ROLE_ADMIN_VALUE));
        sce.getServletContext().setAttribute(ApplicationConstants.ROLE_USER_KEY,
                new Role(ApplicationConstants.ROLE_USER_VALUE));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}