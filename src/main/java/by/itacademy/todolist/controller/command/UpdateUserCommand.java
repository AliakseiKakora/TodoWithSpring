package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;

public class UpdateUserCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
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
                default:
                    throw new RuntimeException();
            }

            profileService.update(profile);
            context.getRequestDispatcher("/?command=AllUsers").forward(request, response);
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY, "blocking user");
        context.getRequestDispatcher("/?command=AllUsers").forward(request, response);
    }
}
