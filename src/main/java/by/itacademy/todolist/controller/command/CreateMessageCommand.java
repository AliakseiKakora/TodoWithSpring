package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreateMessageCommand extends FrontCommand {

    private static final String LOGIN_VIEW = "/guest?command=LoginView&";
    private static final String MAIN_VIEW = "/?command=MainView&";
    private static final String MESSAGE = "=submit message";

    @Override
    public void process() throws ServletException, IOException {
        String contextPath = request.getContextPath();
        try {
            long userId = Long.parseLong(request.getParameter(ApplicationConstants.USER_ID_KEY));
            String userMessage = request.getParameter(ApplicationConstants.MESSAGE_KEY);

            User user = userService.getById(userId);
            Message message = Message.builder()
                    .message(userMessage)
                    .dateAdded(LocalDateTime.now())
                    .user(user)
                    .build();

            messageService.save(message);
            if (request.getSession().getAttribute(ApplicationConstants.USER_KEY) == null) {
                response.sendRedirect(contextPath + LOGIN_VIEW +
                        ApplicationConstants.SUCCESSFUL_KEY + MESSAGE);
            } else {
                response.sendRedirect(contextPath + MAIN_VIEW +
                        ApplicationConstants.SUCCESSFUL_KEY + MESSAGE);
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + LOGIN_VIEW +
                ApplicationConstants.ERROR_KEY + MESSAGE);
    }
}