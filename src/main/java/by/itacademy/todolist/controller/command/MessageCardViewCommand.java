package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Message;

import javax.servlet.ServletException;
import java.io.IOException;

public class MessageCardViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        try {
            long messageId = Long.parseLong(request.getParameter(ApplicationConstants.MESSAGE_ID));
            Message message = messageService.getById(messageId);
            request.setAttribute(ApplicationConstants.MESSAGE_KEY, message);
            context.getRequestDispatcher(ApplicationConstants.MESSAGE_CARD_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/?command=AllMessages&" +
                ApplicationConstants.ERROR_KEY + "=search message");
    }
}
