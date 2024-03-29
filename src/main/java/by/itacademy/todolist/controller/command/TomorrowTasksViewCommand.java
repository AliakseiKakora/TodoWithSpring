package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class TomorrowTasksViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
        String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);
        try {
            List<Task> tomorrowTasks = taskService.getTomorrowUserTasks(user.getId());
            request.setAttribute(ApplicationConstants.TASKS_KEY, tomorrowTasks);
            request.setAttribute(ApplicationConstants.SECTION_KEY, ApplicationConstants.SECTION_TOMORROW);
            request.setAttribute(ApplicationConstants.TASK_TITLE, ApplicationConstants.TASK_TOMORROW_TITLE);
            request.setAttribute(ApplicationConstants.ERROR_KEY, errorMessage);
            context.getRequestDispatcher(ApplicationConstants.TASKS_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, ApplicationConstants.TASK_SEARCH_ERROR_MSG);
        context.getRequestDispatcher(ApplicationConstants.TASKS_JSP).forward(request, response);
    }
}
