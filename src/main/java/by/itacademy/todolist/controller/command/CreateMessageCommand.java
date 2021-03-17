package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Message;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;
import java.time.LocalDateTime;

public class CreateMessageCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String contextPath = request.getContextPath();
        try {
            long userId = Long.parseLong(request.getParameter(ApplicationConstants.USER_ID_KEY));
            String userMessage = request.getParameter(ApplicationConstants.MESSAGE_KEY);

            User user = userService.getById(userId);
            Message message = Message.builder().
                    message(userMessage).
                    dateAdded(LocalDateTime.now()).
                    user(user).
                    build();

            messageService.create(message);
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(contextPath + "/guest?command=LoginView&" +
                        ApplicationConstants.SUCCESSFUL_KEY + "=submit message");
            } else {
                response.sendRedirect(contextPath + "/?command=MainView&" +
                        ApplicationConstants.SUCCESSFUL_KEY + "=submit message");
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + "/guest?command=LoginView&" +
                ApplicationConstants.ERROR_KEY + "=submit message");
    }
}