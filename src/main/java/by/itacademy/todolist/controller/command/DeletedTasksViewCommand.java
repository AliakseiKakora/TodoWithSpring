package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class DeletedTasksViewCommand extends FrontCommand {

    private static final String ERROR_MESSAGE = "A tasks search error has occurred.";

    @Override
    public void process() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
        String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);
        try {
            List<Task> deletedTasks = taskService.getDeletedUserTasks(user.getId());
            request.setAttribute(ApplicationConstants.TASKS_KEY, deletedTasks);
            request.setAttribute(ApplicationConstants.SECTION_KEY, ApplicationConstants.SECTION_DELETED);
            request.setAttribute(ApplicationConstants.TASK_TITLE, ApplicationConstants.TASK_DELETED_TITLE);
            request.setAttribute(ApplicationConstants.ERROR_KEY, errorMessage);
            context.getRequestDispatcher(ApplicationConstants.TASKS_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, ERROR_MESSAGE);
        context.getRequestDispatcher(ApplicationConstants.TASKS_JSP).forward(request, response);
    }
}