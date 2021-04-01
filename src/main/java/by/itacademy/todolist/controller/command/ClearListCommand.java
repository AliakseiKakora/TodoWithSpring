package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class ClearListCommand extends FrontCommand {

    private static final String DELETED_TASKS_VIEW = "/?command=DeletedTasksView";
    private static final String ERROR_MESSAGE = "=deleting tasks";

    @Override
    public void process() throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            taskService.deleteAllUserDeletedTask(user.getId());
            response.sendRedirect(DELETED_TASKS_VIEW);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(DELETED_TASKS_VIEW + "&"
                + ApplicationConstants.ERROR_KEY + ERROR_MESSAGE);
    }
}