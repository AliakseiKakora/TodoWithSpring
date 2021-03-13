package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class TodayTasksViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
        String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);
        try {
            List<Task> todayTasks = taskService.getTodayUserTasks(user.getId());
            request.setAttribute(ApplicationConstants.TASKS_KEY, todayTasks);
            request.setAttribute(ApplicationConstants.SECTION_KEY, ApplicationConstants.SECTION_TODAY);
            request.setAttribute(ApplicationConstants.TASK_TITLE, ApplicationConstants.TASK_TODAY_TITLE);
            request.setAttribute(ApplicationConstants.ERROR_KEY, errorMessage);
            context.getRequestDispatcher(ApplicationConstants.TASKS_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            System.out.println("A tasks search error has occurred. " + user);
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, "A tasks search error has occurred.");
        context.getRequestDispatcher(ApplicationConstants.TASKS_JSP).forward(request, response);
    }
}
