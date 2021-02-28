package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.util.DependencyManager;
import by.itacademy.todolist.util.DependencyManagerImpl;

import javax.servlet.ServletException;
import java.io.IOException;

public class SectionTasksViewCommand extends FrontCommand {

    private final DependencyManager dependencyManager = DependencyManagerImpl.getInstance();

    @Override
    public void process() throws ServletException, IOException {
        String section = request.getParameter(ApplicationConstants.SECTION_KEY);
        FrontCommand frontCommand;

        switch (section) {
            case ApplicationConstants.SECTION_TODAY:
                frontCommand = new TodayTasksViewCommand();
                break;
            case ApplicationConstants.SECTION_TOMORROW:
                frontCommand = new TomorrowTasksViewCommand();
                break;
            case ApplicationConstants.SECTION_SOME_DAY:
                frontCommand = new SomeDayTasksViewCommand();
                break;
            case ApplicationConstants.SECTION_FIXED:
                frontCommand = new FixedTasksViewCommand();
                break;
            case ApplicationConstants.SECTION_DELETED:
                frontCommand = new DeletedTasksViewCommand();
                break;
            default:
                throw new RuntimeException();
        }

        frontCommand.init(context, request, response,
                dependencyManager.getUsersService(),
                dependencyManager.getProfileService(),
                dependencyManager.getTaskService());
        frontCommand.process();
    }
}