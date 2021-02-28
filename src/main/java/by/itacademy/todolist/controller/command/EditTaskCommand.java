package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EditTaskCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        try {
            long taskId = Long.parseLong(request.getParameter(ApplicationConstants.TASK_ID));
            String name = request.getParameter(ApplicationConstants.TASK_NAME);
            String description = request.getParameter(ApplicationConstants.TASK_DESCRIPTION);
            String date = request.getParameter(ApplicationConstants.TASK_DATE);
            String time = request.getParameter(ApplicationConstants.TASK_TIME);

            LocalDateTime dateCompleted = LocalDateTime.of(LocalDate.parse(date), LocalTime.parse(time));

            Task task = taskService.getTaskById(taskId);
            task.setName(name);
            task.setDescription(description);
            task.setDateCompletion(dateCompleted);

            taskService.updateTask(task);
            request.setAttribute(ApplicationConstants.SUCCESSFUL_KEY, ApplicationConstants.DATA_UPDATED_MSG);
            request.setAttribute(ApplicationConstants.TASK_KEY, task);
            context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
            return;

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute(ApplicationConstants.ERROR_KEY, "task update error");
        context.getRequestDispatcher(ApplicationConstants.EDIT_TASK_JSP).forward(request, response);
    }
}
