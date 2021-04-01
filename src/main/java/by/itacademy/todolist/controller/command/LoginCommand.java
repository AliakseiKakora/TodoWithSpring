package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginCommand extends FrontCommand {

    private static final String LOGIN_VIEW = "/guest?command=LoginView&";
    private static final String ERROR_MESSAGE = "=Invalid user data";

    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter(ApplicationConstants.LOGIN_KEY);
        String password = request.getParameter(ApplicationConstants.PASSWORD_KEY);
        String contextPath = request.getContextPath();

        try {
            User user = userService.getUserByLoginAndPassword(login, password);
            request.getSession().setAttribute(ApplicationConstants.USER_KEY, user);
            response.sendRedirect( contextPath + ApplicationConstants.MAIN_JSP);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + LOGIN_VIEW +
                ApplicationConstants.ERROR_KEY + ERROR_MESSAGE);
    }
}
