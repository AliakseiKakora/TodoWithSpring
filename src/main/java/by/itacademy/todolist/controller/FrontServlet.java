package by.itacademy.todolist.controller;

import by.itacademy.todolist.controller.command.FrontCommand;
import by.itacademy.todolist.controller.command.UnknownCommand;
import by.itacademy.todolist.util.DependencyManager;
import by.itacademy.todolist.util.DependencyManagerImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FrontServlet", urlPatterns = "")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class FrontServlet extends HttpServlet {

    private final DependencyManager dependencyManager = DependencyManagerImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("command") == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        FrontCommand command = getCommand(request);
        command.init(getServletContext(), request, response,
                dependencyManager.getUsersService(),
                dependencyManager.getProfileService(),
                dependencyManager.getTaskService(),
                dependencyManager.getFileService());
        command.process();
    }

    private FrontCommand getCommand(HttpServletRequest request) {
        try {
            Class type = Class.forName(String.format("by.itacademy.todolist.controller.command.%sCommand",
                    request.getParameter("command")));

            return (FrontCommand) type.asSubclass(FrontCommand.class).newInstance();

        } catch (Exception e) {
            return new UnknownCommand();
        }
    }
}
