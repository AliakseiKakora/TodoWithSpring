package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.model.Task;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteFileCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        Task task = null;
        long taskId = 0;
        try {
            long fileId = Long.parseLong(request.getParameter(ApplicationConstants.FILE_ID));
            taskId = Long.parseLong(request.getParameter(ApplicationConstants.TASK_ID));
            FileInfo fileInfo = fileService.getById(fileId);
            fileService.delete(fileInfo);

            task = taskService.getTaskById(taskId);
            request.setAttribute(ApplicationConstants.TASK_KEY, task);
            request.setAttribute(ApplicationConstants.SUCCESSFUL_KEY, "file was deleted");
            context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (task == null) {
            task = taskService.getTaskById(taskId);
        }
        request.setAttribute(ApplicationConstants.TASK_KEY, task);
        request.setAttribute(ApplicationConstants.ERROR_KEY, "file delete error ");
        context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
    }
}
