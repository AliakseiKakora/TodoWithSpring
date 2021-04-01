package by.itacademy.todolist.controller;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.controller.command.FrontCommand;
import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.util.DependencyManager;
import by.itacademy.todolist.util.DependencyManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FrontServlet", urlPatterns = {"", "/guest"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class FrontServlet extends HttpServlet {

    private final DependencyManager dependencyManager = DependencyManagerImpl.getInstance();

    private static final String ERROR_VIEW = "/?command=ErrorView";
    private static final String ERROR_MESSAGE = "Error found command + ";
    private static final String COMMAND = "by.itacademy.todolist.controller.command.%sCommand";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contextPath = request.getContextPath();
        if (request.getParameter(ApplicationConstants.COMMAND_KEY) == null) {
            response.sendRedirect(contextPath + ApplicationConstants.INDEX_JSP);
            return;
        }

        try {
            FrontCommand command = getCommand(request);
            command.init(getServletContext(), request, response,
                    dependencyManager.getUsersService(),
                    dependencyManager.getProfileService(),
                    dependencyManager.getTaskService(),
                    dependencyManager.getFileService(),
                    dependencyManager.getMessageService());
            command.process();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(contextPath + ERROR_VIEW);
        }
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            Class type = Class.forName(String.format(COMMAND,
                    request.getParameter(ApplicationConstants.COMMAND_KEY)));

            return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();
        } catch (Exception e) {
            throw  new RuntimeException(ERROR_MESSAGE + request.getParameter(ApplicationConstants.COMMAND_KEY));
        }
    }

    @Override
    public void init() throws ServletException {
        getServletContext().setAttribute(ApplicationConstants.ROLE_ADMIN_KEY,
                new Role(ApplicationConstants.ROLE_ADMIN_VALUE));
        getServletContext().setAttribute(ApplicationConstants.ROLE_USER_KEY,
                new Role(ApplicationConstants.ROLE_USER_VALUE));
    }
}
