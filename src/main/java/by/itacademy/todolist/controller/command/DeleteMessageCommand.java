package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class DeleteMessageCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String contextPath = request.getContextPath();
        try {
            long messageId = Long.parseLong(request.getParameter(ApplicationConstants.MESSAGE_ID));
            messageService.delete(messageId);
            response.sendRedirect(contextPath + "/?command=AllMessages");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + "/?command=AllMessages&" +
                ApplicationConstants.ERROR_KEY + "=deleting message");
    }
}