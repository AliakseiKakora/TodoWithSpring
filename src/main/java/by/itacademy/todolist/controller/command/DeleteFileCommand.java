package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.FileInfo;
import by.itacademy.todolist.model.Task;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteFileCommand extends FrontCommand {

    private static final String SUCCESSFUL_MESSAGE = "file was deleted";
    private static final String ERROR_MESSAGE = "file delete error";

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
            request.setAttribute(ApplicationConstants.SUCCESSFUL_KEY, SUCCESSFUL_MESSAGE);
            context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (task == null) {
            task = taskService.getTaskById(taskId);
        }
        request.setAttribute(ApplicationConstants.TASK_KEY, task);
        request.setAttribute(ApplicationConstants.ERROR_KEY, ERROR_MESSAGE);
        context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
    }
}