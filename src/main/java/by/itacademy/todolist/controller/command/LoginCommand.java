package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            User user = userService.getUserByLoginAndPassword(login, password);
            request.getSession().setAttribute("user", user);
            context.getRequestDispatcher("/main.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            System.out.println("User not found");
        }
        request.setAttribute("error", "Invalid user data");
        context.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
