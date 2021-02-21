package by.itacademy.todolist.controller.command;

import java.io.IOException;

public class ProfileViewCommand extends FrontCommand {

    @Override
    public void process() throws IOException {
        response.sendRedirect("profile.jsp");
    }
}
