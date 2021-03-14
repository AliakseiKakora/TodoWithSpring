package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;

import javax.servlet.ServletException;
import java.io.IOException;

public class UpdateTaskCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String section = request.getParameter(ApplicationConstants.SECTION_KEY);
        String contextPath = request.getContextPath();
        try {
            String action = request.getParameter(ApplicationConstants.ACTION_KEY);
            long takId = Long.parseLong(request.getParameter(ApplicationConstants.TASK_ID));
            Task task = taskService.getTaskById(takId);

            switch (action) {
                case ApplicationConstants.TASK_ACTION_DELETE:
                    task.setDeleted(true);
                    break;
                case ApplicationConstants.TASK_ACTION_FIXED:
                    task.setCompleted(true);
                    break;
                case ApplicationConstants.TASK_ACTION_RESTORE:
                    task.setCompleted(false);
                    task.setDeleted(false);
                    break;
                case ApplicationConstants.TASK_ACTION_FULL_DELETE:
                    if (task.getFileInfo().getPath() != null) {
                        fileService.delete(task.getFileInfo().getId());
                    }
                    taskService.deleteTask(takId);
                    response.sendRedirect(contextPath + "/?command=SectionTasksView&section=" + section);
                    return;
                default:
                    throw new RuntimeException();
            }

            taskService.updateTask(task);
            response.sendRedirect(contextPath + "/?command=SectionTasksView&section=" + section);
            return;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fixed task");
        }

        response.sendRedirect(contextPath + "/command=SectionTasksView&section=" +
                section + "&" + ApplicationConstants.ERROR_KEY + "=Error fixed task");
    }
}