package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteUserCommand extends FrontCommand {

    private static final String ALL_USERS = "/?command=AllUsers";
    private static final String ERROR_MESSAGE = "=deleting user";

    @Override
    public void process() throws ServletException, IOException {
        if (!checkAdminRole()) {
            return;
        }
        String contextPath = request.getContextPath();
        try {
            long userId = Long.parseLong(request.getParameter(ApplicationConstants.USER_ID_KEY));
            userService.deleteById(userId);
            response.sendRedirect(contextPath + ALL_USERS);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + ALL_USERS + "&" +
                ApplicationConstants.ERROR_KEY + ERROR_MESSAGE);
    }
}