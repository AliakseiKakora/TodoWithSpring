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

    private static final String DATE_FORMAT = "%sT23:59";
    private static final String UPLOAD_FILE = "/?command=UploadFile&taskId=";
    private static final String CREATE_PARAMETER = "&create=create";
    private static final String ADD_TASK_VIEW_SECTION = "/?command=AddTaskView&section=";

    @Override
    public void process() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
        String section = request.getParameter(ApplicationConstants.SECTION_KEY);
        String name = request.getParameter(ApplicationConstants.TASK_NAME);
        String description = request.getParameter(ApplicationConstants.TASK_DESCRIPTION);
        String date = request.getParameter(ApplicationConstants.TASK_DATE);
        String time = request.getParameter(ApplicationConstants.TASK_TIME);

        String today = String.format(DATE_FORMAT, LocalDate.now().toString());

        String contextPath = request.getContextPath();

        try {
            Task task = Task.builder().name(name).description(description).dateAdded(LocalDateTime.now()).build();
            task.setUser(user);
            switch (section) {
                case ApplicationConstants.SECTION_SOME_DAY:
                    LocalDate localDate = LocalDate.parse(date);
                    LocalTime localTime = LocalTime.parse(time);
                    LocalDateTime timeCompleted = LocalDateTime.of(localDate, localTime);
                    task.setDateCompletion(timeCompleted);
                    break;
                case ApplicationConstants.SECTION_TODAY:
                    task.setDateCompletion(LocalDateTime.parse(today));
                    break;
                case ApplicationConstants.SECTION_TOMORROW:
                    LocalDateTime tomorrow = LocalDateTime.parse(today).plusDays(1);
                    task.setDateCompletion(tomorrow);
                    break;
            }
            task = taskService.save(task);
            long taskId = task.getId();
            request.getRequestDispatcher(UPLOAD_FILE
                    + taskId + CREATE_PARAMETER).include(request, response);

            response.sendRedirect(contextPath + ADD_TASK_VIEW_SECTION + section + "&"
                    + ApplicationConstants.SUCCESSFUL_KEY + "=" + ApplicationConstants.SUCCESSFUL_TASK_ADDED_MSG);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + ADD_TASK_VIEW_SECTION + section + "&"
                + ApplicationConstants.ERROR_KEY +  "=" + ApplicationConstants.ERROR_TASK_ADDED_MSG);
    }
}