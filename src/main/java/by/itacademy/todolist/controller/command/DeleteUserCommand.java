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
            context.getRequestDispatcher("/?command=AllUsers").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, "blocking user");
        context.getRequestDispatcher("/?command=AllUsers").forward(request, response);

    }
}
