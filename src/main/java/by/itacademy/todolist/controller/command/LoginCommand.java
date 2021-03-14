package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String contextPath = request.getContextPath();

        try {
            User user = userService.getUserByLoginAndPassword(login, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect( contextPath + "/main.jsp");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/guest?command=LoginView&" +
                ApplicationConstants.ERROR_KEY + "=Invalid user data");
    }
}
