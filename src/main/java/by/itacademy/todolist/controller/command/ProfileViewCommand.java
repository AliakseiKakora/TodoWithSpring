package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class ProfileViewCommand extends FrontCommand {

    @Override
    public void process() throws IOException, ServletException {
        try {
            String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);
            String successfulMessage = request.getParameter(ApplicationConstants.SUCCESSFUL_KEY);
            User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
            user = userService.getById(user.getId());
            request.setAttribute(ApplicationConstants.ERROR_KEY, errorMessage);
            request.setAttribute(ApplicationConstants.SUCCESSFUL_KEY, successfulMessage);
            request.setAttribute(ApplicationConstants.USER_KEY, user);
            context.getRequestDispatcher(ApplicationConstants.PROFILE_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/?command=MainView");
    }
}
