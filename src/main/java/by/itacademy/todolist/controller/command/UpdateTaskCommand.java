package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;

import javax.servlet.ServletException;
import java.io.IOException;

public class UpdateTaskCommand extends FrontCommand {

    private static final String COMMAND_TASKS_VIEW_SECTION = "/?command=SectionTasksView&section=";
    private static final String ERROR_MESSAGE = "=Error fixed task";

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
                    if (task.getFileInfo() != null) {
                        fileService.delete(task.getFileInfo());
                    }
                    taskService.deleteTask(takId);
                    response.sendRedirect(contextPath + COMMAND_TASKS_VIEW_SECTION + section);
                    return;
                default:
                    throw new RuntimeException();
            }

            taskService.updateTask(task);
            response.sendRedirect(contextPath + COMMAND_TASKS_VIEW_SECTION + section);
            return;

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect(contextPath + COMMAND_TASKS_VIEW_SECTION +
                section + "&" + ApplicationConstants.ERROR_KEY + ERROR_MESSAGE);
    }
}