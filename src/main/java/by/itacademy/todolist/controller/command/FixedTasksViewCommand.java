package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class FixedTasksViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);

        try {
            List<Task> fixedTasks = taskService.getFixedUserTasks(user.getId());
            request.setAttribute(ApplicationConstants.TASKS_KEY, fixedTasks);
            request.setAttribute(ApplicationConstants.SECTION_KEY, ApplicationConstants.SECTION_FIXED);
            request.setAttribute(ApplicationConstants.TASK_TITLE, ApplicationConstants.TASK_FIXED_TITLE);
            context.getRequestDispatcher(ApplicationConstants.TASKS_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            System.out.println("A tasks search error has occurred. " + user);
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, "A tasks search error has occurred.");
        context.getRequestDispatcher(ApplicationConstants.TASKS_JSP).forward(request, response);
    }
}
