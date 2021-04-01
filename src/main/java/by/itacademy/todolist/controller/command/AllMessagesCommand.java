package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Message;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.List;

public class AllMessagesCommand extends FrontCommand {

    private static final String ERROR_MESSAGE = "search messages";

    @Override
    public void process() throws ServletException, IOException {
        if (!checkAdminRole()) {
            return;
        }
        try {
            String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);
            List<Message> messages = messageService.getAll();
            request.setAttribute(ApplicationConstants.MESSAGES_KEY, messages);
            request.setAttribute(ApplicationConstants.ERROR_KEY, errorMessage);
            context.getRequestDispatcher(ApplicationConstants.ALL_MESSAGES_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, ERROR_MESSAGE);
        context.getRequestDispatcher(ApplicationConstants.ALL_MESSAGES_JSP).forward(request, response);
    }
}