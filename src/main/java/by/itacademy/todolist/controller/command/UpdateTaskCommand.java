package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.util.DependencyManager;
import by.itacademy.todolist.util.DependencyManagerImpl;

import javax.servlet.ServletException;
import java.io.IOException;

public class UpdateTaskCommand extends FrontCommand {

    private final DependencyManager dependencyManager = DependencyManagerImpl.getInstance();

    @Override
    public void process() throws ServletException, IOException {
        SectionTasksViewCommand sectionTasksViewCommand = new SectionTasksViewCommand();
        sectionTasksViewCommand.init(context, request, response,
                dependencyManager.getUsersService(),
                dependencyManager.getProfileService(),
                dependencyManager.getTaskService(),
                dependencyManager.getFileService());

        try {
            String action = request.getParameter(ApplicationConstants.TASK_ACTION_KEY);
            long takId = Long.parseLong(request.getParameter(ApplicationConstants.TASK_ID));
            Task task = taskService.getTaskById(takId);

            switch (action) {
                case ApplicationConstants.TASK_ACTION_DELETE:
                    task.setDeleted(true);
                    break;
                case ApplicationConstants.TASK_ACTION_FIXED:
                    task.setCompleted(true);
                    break;
                case ApplicationConstants.TASK_ACTION_FULL_DELETE:
                    if (task.getFileInfo().getPath() != null) {
                        fileService.delete(task.getFileInfo().getId());
                    }
                    taskService.deleteTask(takId);
                    sectionTasksViewCommand.process();
                    return;
                default:
                    throw new RuntimeException();
            }

            taskService.updateTask(task);
            sectionTasksViewCommand.process();
            return;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error fixed task");
        }

        request.setAttribute(ApplicationConstants.ERROR_KEY, "Error fixed task");
        sectionTasksViewCommand.process();

    }
}
