package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class AddTaskViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String section = request.getParameter(ApplicationConstants.SECTION_KEY);
        request.setAttribute(ApplicationConstants.SECTION_KEY, section);
        context.getRequestDispatcher(ApplicationConstants.ADD_TASK_JSP).forward(request, response);
    }
}