package by.itacademy.todolist.controller.command;

import javax.servlet.ServletException;
import java.io.IOException;

public class RegistrationViewCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String context = request.getContextPath();
        response.sendRedirect(context + "/registration.jsp");
    }
}
