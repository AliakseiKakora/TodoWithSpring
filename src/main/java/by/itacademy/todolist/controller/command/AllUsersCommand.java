package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AllUsersCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        try {
            String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);
            List<User> allUsers = userService.getAllUsers();
            request.setAttribute(ApplicationConstants.USERS_KEY, allUsers);
            request.setAttribute(ApplicationConstants.ERROR_KEY, errorMessage);
            context.getRequestDispatcher(ApplicationConstants.ALL_USERS_JSP).forward(request, response);
            return;

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, "search users");
        context.getRequestDispatcher(ApplicationConstants.ALL_USERS_JSP).forward(request, response);
    }
}
