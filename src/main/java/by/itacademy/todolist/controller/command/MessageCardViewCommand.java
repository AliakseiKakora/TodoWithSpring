package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Message;

import javax.servlet.ServletException;
import java.io.IOException;

public class MessageCardViewCommand extends FrontCommand {

    private static final String ALL_MESSAGES = "/?command=AllMessages&";
    private static final String ERROR_MESSAGE = "=search message";

    @Override
    public void process() throws ServletException, IOException {
        if (!checkAdminRole()) {
            return;
        }
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
        response.sendRedirect(contextPath + ALL_MESSAGES +
                ApplicationConstants.ERROR_KEY + ERROR_MESSAGE);
    }
}
