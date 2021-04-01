package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegistrationCommand extends FrontCommand {

    private static final String REGISTRATION_VIEW = "/guest?command=RegistrationView&";

    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter(ApplicationConstants.LOGIN_KEY);
        String password = request.getParameter(ApplicationConstants.PASSWORD_KEY);
        String email = request.getParameter(ApplicationConstants.EMAIL_KEY);
        String contextPath = request.getContextPath();

        try {
            if (profileService.existLoginOrEmail(login, email)) {
                response.sendRedirect(contextPath + REGISTRATION_VIEW +
                        ApplicationConstants.ERROR_KEY + "=Login or email is busy");
                return;
            }
            Profile profile = Profile.builder().isEnable(true).login(login).password(password).build();
            User user = new User();
            user.setEmail(email);
            user.addProfile(profile);
            user = userService.save(user);
            request.getSession().setAttribute(ApplicationConstants.USER_KEY, user);
            response.sendRedirect(contextPath + ApplicationConstants.MAIN_JSP);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + REGISTRATION_VIEW +
                ApplicationConstants.ERROR_KEY + "=Registration error");
    }
}