package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;


public class UpdateProfileCommand extends FrontCommand {

    private static final String COMMAND_PROFILE_VIEW = "/?command=ProfileView&";

    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter(ApplicationConstants.LOGIN_KEY);
        String password = request.getParameter(ApplicationConstants.PASSWORD_KEY);
        String email = request.getParameter(ApplicationConstants.EMAIL_KEY);
        String surname = request.getParameter(ApplicationConstants.USER_SURNAME_KEY);
        String name = request.getParameter(ApplicationConstants.USER_NAME_KEY);

        User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
        Profile profile = user.getProfile();

        String contextPath = request.getContextPath();

        try {
            profile.setLogin(login);
            profile.setPassword(password);
            user.setEmail(email);
            user.setSurname(surname);
            user.setName(name);
            userService.update(user);

            response.sendRedirect(contextPath + COMMAND_PROFILE_VIEW +
                    ApplicationConstants.SUCCESSFUL_KEY + "=" + ApplicationConstants.DATA_UPDATED_MSG);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect(contextPath + COMMAND_PROFILE_VIEW +
                ApplicationConstants.ERROR_KEY + "=" + ApplicationConstants.DATA_UPDATED_MSG);
    }

}