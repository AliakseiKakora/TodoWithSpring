package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.Role;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            List<Role> roles = new ArrayList<>();

            //todo change work with roles

           // roles.add(Role.USER);
            Profile profile = Profile.builder().isEnable(true).login(login).password(password).build();
           // User user = User.builder().profile(profile).email(email).roles(roles).build();
           // user = userService.create(user);
          //  request.getSession().setAttribute("user", user);
            response.sendRedirect(contextPath + ApplicationConstants.MAIN_JSP);
            return;
        } catch (Exception e) {
            System.out.println("Registration error");
        }
        response.sendRedirect(contextPath + "/guest?command=RegistrationView&" +
                ApplicationConstants.ERROR_KEY + "=Registration error");
    }
}