package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;

import javax.servlet.ServletException;
import java.io.IOException;

public class SectionTasksViewCommand extends FrontCommand {

    private static final String COMMAND_TODAY_TASKS_VIEW = "/?command=TodayTasksView";
    private static final String COMMAND_TOMORROW_TASKS_VIEW = "/?command=TomorrowTasksView";
    private static final String COMMAND_SOMEDAY_TASKS_VIEW = "/?command=SomeDayTasksView";
    private static final String COMMAND_FIXED_TASKS_VIEW = "/?command=FixedTasksView";
    private static final String COMMAND_DELETED_TASKS_VIEW = "/?command=DeletedTasksView";

    @Override
    public void process() throws ServletException, IOException {
        String section = request.getParameter(ApplicationConstants.SECTION_KEY);
        String contextPath = request.getContextPath();

        switch (section) {
            case ApplicationConstants.SECTION_TODAY:
                context.getRequestDispatcher(COMMAND_TODAY_TASKS_VIEW).forward(request, response);
                break;
            case ApplicationConstants.SECTION_TOMORROW:
                context.getRequestDispatcher(COMMAND_TOMORROW_TASKS_VIEW).forward(request, response);
                break;
            case ApplicationConstants.SECTION_SOME_DAY:
                context.getRequestDispatcher(COMMAND_SOMEDAY_TASKS_VIEW).forward(request, response);
                break;
            case ApplicationConstants.SECTION_FIXED:
                context.getRequestDispatcher(COMMAND_FIXED_TASKS_VIEW).forward(request, response);
                break;
            case ApplicationConstants.SECTION_DELETED:
                context.getRequestDispatcher(COMMAND_DELETED_TASKS_VIEW).forward(request, response);
                break;
            default:
                response.sendRedirect(contextPath + ApplicationConstants.MAIN_JSP);
        }
    }
}