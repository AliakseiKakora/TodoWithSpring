package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;

public class UploadFileCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String path = request.getServletContext().getRealPath("");
        path = path.replace('\\', '/');

        String fullSavePath;
        if (path.endsWith("/")) {
            fullSavePath = path + ApplicationConstants.SAVE_DIRECTORY;
        } else {
            fullSavePath = path + "/" + ApplicationConstants.SAVE_DIRECTORY;
        }

        Task task = null;
        long taskId = 0;
        try {
            Part part = request.getPart("file");
            taskId = Long.parseLong(request.getParameter(ApplicationConstants.TASK_ID));
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);

            fileService.addFileInfoForTask(part, taskId, user.getId(), fullSavePath);
            task = taskService.getTaskById(taskId);
            String filePath = task.getFileInfo().getDirectory() + task.getFileInfo().getName();
            request.setAttribute(ApplicationConstants.FILE_PATH, filePath);
            request.setAttribute(ApplicationConstants.TASK_KEY, task);
            request.setAttribute(ApplicationConstants.SUCCESSFUL_KEY, "file upload");
            context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (task == null) {
            task = taskService.getTaskById(taskId);
        }
        request.setAttribute(ApplicationConstants.TASK_KEY, task);
        request.setAttribute(ApplicationConstants.ERROR_KEY, "file upload error ");
        context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
    }

}
