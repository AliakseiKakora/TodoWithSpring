package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class UserCardViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        try {
            long userId = Long.parseLong(request.getParameter(ApplicationConstants.USER_ID_KEY));
            User user = userService.getById(userId);
            request.setAttribute(ApplicationConstants.USER_KEY, user);
            context.getRequestDispatcher(ApplicationConstants.USER_CARD_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/?command=AllUsers&" +
                ApplicationConstants.ERROR_KEY + "=search user");
    }
}