package by.itacademy.todolist.controller.command;

import javax.servlet.ServletException;
import java.io.IOException;

public class LoginViewCommand  extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        String context = request.getContextPath();
        response.sendRedirect(context+ "/login.jsp");
    }
}
