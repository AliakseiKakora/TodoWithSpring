package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteMessageCommand extends FrontCommand {

    private static final String ALL_MESSAGES = "/?command=AllMessages";
    private static final String ERROR_MESSAGE = "=deleting message";

    @Override
    public void process() throws ServletException, IOException {
        if (!checkAdminRole()) {
            return;
        }
        String contextPath = request.getContextPath();
        try {
            long messageId = Long.parseLong(request.getParameter(ApplicationConstants.MESSAGE_ID));
            messageService.delete(messageId);
            response.sendRedirect(contextPath + ALL_MESSAGES);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + ALL_MESSAGES + "&" +
                ApplicationConstants.ERROR_KEY + ERROR_MESSAGE);
    }
}