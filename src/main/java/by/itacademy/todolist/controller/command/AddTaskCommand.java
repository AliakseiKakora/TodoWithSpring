package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Task;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AddTaskCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
        String section = request.getParameter(ApplicationConstants.SECTION_KEY);
        String name = request.getParameter(ApplicationConstants.TASK_NAME);
        String description = request.getParameter(ApplicationConstants.TASK_DESCRIPTION);
        String date = request.getParameter(ApplicationConstants.TASK_DATE);
        String time = request.getParameter(ApplicationConstants.TASK_TIME);

        try {
            Task task = Task.builder().name(name).description(description).dateAdded(LocalDateTime.now()).build();
            switch (section) {
                case ApplicationConstants.SECTION_SOME_DAY:
                    LocalDate localDate = LocalDate.parse(date);
                    LocalTime localTime = LocalTime.parse(time);
                    LocalDateTime timeCompleted = LocalDateTime.of(localDate, localTime);
                    task.setDateCompletion(timeCompleted);
                    break;
                case ApplicationConstants.SECTION_TODAY:
                    task.setDateCompletion(LocalDateTime.now());
                    break;
                case ApplicationConstants.SECTION_TOMORROW:
                    task.setDateCompletion(LocalDateTime.now().plusDays(1));
                    break;
            }
            taskService.createTaskForUser(task, user.getId());
            request.setAttribute(ApplicationConstants.SUCCESSFUL_KEY, ApplicationConstants.SUCCESSFUL_TASK_ADDED_MSG);
            context.getRequestDispatcher(ApplicationConstants.ADD_TASK_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            System.out.println("Task adding error");
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, ApplicationConstants.ERROR_TASK_ADDED_MSG);
        context.getRequestDispatcher(ApplicationConstants.ADD_TASK_JSP).forward(request, response);
    }
}
