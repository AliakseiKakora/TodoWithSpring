package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteUserCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        try {
            long userId = Long.parseLong(request.getParameter(ApplicationConstants.USER_ID_KEY));
            taskService.deleteAllUserTasks(userId);
            userService.deleteById(userId);
            response.sendRedirect("/?command=AllUsers");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/?command=AllUsers&" +
                ApplicationConstants.ERROR_KEY + "=deleting user");
    }
}