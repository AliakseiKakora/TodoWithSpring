package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;

import javax.servlet.ServletException;
import java.io.IOException;

public class EditTaskViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {

        try {
            long taskId = Long.parseLong(request.getParameter(ApplicationConstants.TASK_ID));
            Task task = taskService.getTaskById(taskId);

            String filePath = task.getFileInfo().getDirectory() + task.getFileInfo().getName();
            request.setAttribute(ApplicationConstants.FILE_PATH, filePath);
            request.setAttribute(ApplicationConstants.TASK_KEY, task);
            context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, "task search error");
        context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
    }
}
