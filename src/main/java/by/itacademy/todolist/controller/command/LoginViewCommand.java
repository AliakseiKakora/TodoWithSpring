package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginViewCommand  extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);
        request.setAttribute(ApplicationConstants.ERROR_KEY, errorMessage);
        context.getRequestDispatcher(ApplicationConstants.LOGIN_JSP).forward(request, response);
    }
}