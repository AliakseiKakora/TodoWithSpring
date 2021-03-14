package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class AdminPageViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String contextPath = request.getContextPath();
        response.sendRedirect( contextPath + ApplicationConstants.ADMIN_PAGE_JSP);
    }
}
