package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class ClearListCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            taskService.deleteAllUserDeletedTask(user.getId());
            context.getRequestDispatcher("/?command=DeletedTasksView").forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, "deleting tasks");
        context.getRequestDispatcher("/?command=DeletedTasksView").forward(request, response);

    }
}
