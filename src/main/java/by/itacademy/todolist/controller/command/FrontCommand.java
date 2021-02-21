package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.service.ProfileService;
import by.itacademy.todolist.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class FrontCommand {
    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    protected UserService userService;
    protected ProfileService profileService;

    public void init(
            ServletContext servletContext,
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            UserService userService,
            ProfileService profileService) {
        this.context = servletContext;
        this.request = servletRequest;
        this.response = servletResponse;
        this.userService = userService;
        this.profileService = profileService;
    }

    public abstract void process() throws ServletException, IOException;
}