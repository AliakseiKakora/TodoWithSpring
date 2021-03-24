package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;

import javax.servlet.ServletException;
import java.io.IOException;

public class TaskViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        try {
            long taskId = Long.parseLong(request.getParameter(ApplicationConstants.TASK_ID));
            String section = request.getParameter(ApplicationConstants.SECTION_KEY);
            Task task = taskService.getTaskById(taskId);

            request.setAttribute(ApplicationConstants.TASK_KEY, task);
            request.setAttribute(ApplicationConstants.SECTION_KEY, section);
            context.getRequestDispatcher(ApplicationConstants.TASK_CARD_JSP).forward(request, response);
            return;

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, "task search");
        context.getRequestDispatcher(ApplicationConstants.TASK_CARD_JSP).forward(request, response);
    }
}