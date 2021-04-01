package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class ErrorViewCommand extends FrontCommand {

    private static final String MAIN_VIEW = "/?command=MainView";

    @Override
    public void process() throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            long userId = user.getId();
            request.setAttribute(ApplicationConstants.USER_ID_KEY, userId);
            context.getRequestDispatcher(ApplicationConstants.ERROR_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + MAIN_VIEW);
    }
}