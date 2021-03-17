package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class MainViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String successful = request.getParameter(ApplicationConstants.SUCCESSFUL_KEY);
        request.setAttribute(ApplicationConstants.SUCCESSFUL_KEY, successful);
        context.getRequestDispatcher(ApplicationConstants.MAIN_JSP).forward(request, response);
    }
}
