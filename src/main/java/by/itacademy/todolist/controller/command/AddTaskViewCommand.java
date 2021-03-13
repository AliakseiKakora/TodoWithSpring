package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddTaskViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String section = request.getParameter(ApplicationConstants.SECTION_KEY);
        String successfulMessage = request.getParameter(ApplicationConstants.SUCCESSFUL_KEY);
        String errorMessage = request.getParameter(ApplicationConstants.ERROR_KEY);

        request.setAttribute(ApplicationConstants.SECTION_KEY, section);
        request.setAttribute(ApplicationConstants.SUCCESSFUL_KEY, successfulMessage);
        request.setAttribute(ApplicationConstants.ERROR_KEY, errorMessage);
        context.getRequestDispatcher(ApplicationConstants.ADD_TASK_JSP).forward(request, response);
    }
}