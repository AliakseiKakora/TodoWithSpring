package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.User;

import javax.servlet.ServletException;
import java.io.IOException;


public class UpdateProfileCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");

        User user = (User) request.getSession().getAttribute(ApplicationConstants.USER_KEY);
        Profile profile = user.getProfile();

        try {
            if (!profile.getLogin().equals(login) || !profile.getPassword().equals(password)) {
                profile.setLogin(login);
                profile.setPassword(password);
                profileService.update(profile);
            }

            if (!user.getEmail().equals(email)
                    || !user.getSurname().equals(surname)
                    || !user.getName().equals(name)) {
                user.setEmail(email);
                user.setSurname(surname);
                user.setName(name);
                user = userService.update(user);
            }

            user = userService.getById(user.getId());
            request.getSession().setAttribute(ApplicationConstants.USER_KEY, user);
            request.setAttribute(ApplicationConstants.SUCCESSFUL_KEY, ApplicationConstants.DATA_UPDATED_MSG);
            context.getRequestDispatcher(ApplicationConstants.PROFILE_JSP).forward(request, response);
            return;
        } catch (Exception e) {
            System.out.println("Error update user data");
        }
        request.setAttribute(ApplicationConstants.ERROR_KEY,  ApplicationConstants.DATA_UPDATED_MSG);
        context.getRequestDispatcher(ApplicationConstants.PROFILE_JSP).forward(request, response);
    }

}