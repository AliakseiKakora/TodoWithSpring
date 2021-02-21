package by.itacademy.todolist.controller.command;

import by.itacademy.todolist.model.Profile;
import by.itacademy.todolist.model.Role;
import by.itacademy.todolist.model.User;

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

        if (profileService.existLoginAndEmail(login, email)) {
           request.setAttribute("error", "Login or email is busy");
           context.getRequestDispatcher("/registration.jsp").forward(request, response);
           return;
        }

        List<Role> roles = new ArrayList<>();
        roles.add(Role.USER);
        Profile profile = Profile.builder().isEnable(true).login(login).password(password).build();
        User user = User.builder().profile(profile).email(email).roles(roles).build();
        user = userService.create(user);
        request.getSession().setAttribute("user", user);
        context.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}
