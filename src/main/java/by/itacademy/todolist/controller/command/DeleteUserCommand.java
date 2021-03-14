package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteUserCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String contextPath = request.getContextPath();
        try {
            long userId = Long.parseLong(request.getParameter(ApplicationConstants.USER_ID_KEY));
            taskService.deleteAllUserTasks(userId);
            messageService.deleteAllUserMessage(userId);
            userService.deleteById(userId);
            response.sendRedirect(contextPath + "/?command=AllUsers");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + "/?command=AllUsers&" +
                ApplicationConstants.ERROR_KEY + "=deleting user");
    }
}