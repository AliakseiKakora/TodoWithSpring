package by.itacademy.todolist.controller.command;

import java.io.IOException;

public class ProfileViewCommand extends FrontCommand {

    @Override
    public void process() throws IOException {
        String context = request.getContextPath();
        response.sendRedirect(context + "/profile.jsp");
    }
}
