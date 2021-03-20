package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegistrationCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String contextPath = request.getContextPath();

        try {
            if (profileService.existLoginOrEmail(login, email)) {
                response.sendRedirect(contextPath + "/guest?command=RegistrationView&" +
                        ApplicationConstants.ERROR_KEY + "=Login or email is busy");
                return;
            }
            Profile profile = Profile.builder().isEnable(true).login(login).password(password).build();
            User user = new User();
            user.setEmail(email);
            user.addProfile(profile);
            user = userService.save(user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(contextPath + ApplicationConstants.MAIN_JSP);
            return;
        } catch (Exception e) {
            System.out.println("Registration error");
        }
        response.sendRedirect(contextPath + "/guest?command=RegistrationView&" +
                ApplicationConstants.ERROR_KEY + "=Registration error");
    }
}