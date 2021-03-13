package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.util.DependencyManager;
import by.itacademy.todolist.util.DependencyManagerImpl;

import javax.servlet.ServletException;
import java.io.IOException;

public class SectionTasksViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String section = request.getParameter(ApplicationConstants.SECTION_KEY);
        String contextPath = request.getContextPath();

        switch (section) {
            case ApplicationConstants.SECTION_TODAY:
                context.getRequestDispatcher("/?command=TodayTasksView").forward(request, response);
                break;
            case ApplicationConstants.SECTION_TOMORROW:
                context.getRequestDispatcher("/?command=TomorrowTasksView").forward(request, response);
                break;
            case ApplicationConstants.SECTION_SOME_DAY:
                context.getRequestDispatcher("/?command=SomeDayTasksView").forward(request, response);
                break;
            case ApplicationConstants.SECTION_FIXED:
                context.getRequestDispatcher("/?command=FixedTasksView").forward(request, response);
                break;
            case ApplicationConstants.SECTION_DELETED:
                context.getRequestDispatcher("/?command=DeletedTasksView").forward(request, response);
                break;
            default:
                response.sendRedirect(contextPath + ApplicationConstants.MAIN_JSP);
        }
    }
}