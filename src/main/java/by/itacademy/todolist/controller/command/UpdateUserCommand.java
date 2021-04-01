package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class UpdateUserCommand extends FrontCommand {

    private static final String COMMAND_ALL_USERS = "/?command=AllUsers";
    private static final String ERROR_MESSAGE = "blocking user";

    @Override
    public void process() throws ServletException, IOException {
        if (!checkAdminRole()) {
            return;
        }
        String contextPath = request.getContextPath();
        try {
            String action = request.getParameter(ApplicationConstants.ACTION_KEY);
            long userId = Long.parseLong(request.getParameter(ApplicationConstants.USER_ID_KEY));
            User user = userService.getById(userId);
            Profile profile = user.getProfile();

            switch (action) {
                case ApplicationConstants.USER_ACTION_BLOCK:
                    profile.setEnable(false);
                    break;
                case ApplicationConstants.USER_ACTION_UNBLOCK:
                    profile.setEnable(true);
                    break;
            }

            profileService.update(profile);
            response.sendRedirect(contextPath + COMMAND_ALL_USERS);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, ERROR_MESSAGE);


        response.sendRedirect(contextPath + COMMAND_ALL_USERS + "&" +
                ApplicationConstants.ERROR_KEY + "=" + ERROR_MESSAGE);
    }
}