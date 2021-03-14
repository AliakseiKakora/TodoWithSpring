package by.itacademy.todolist.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutCommand extends FrontCommand {

    @Override
    public void process() throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        String contextPath = request.getContextPath();
        response.sendRedirect(contextPath + "/login.jsp");
    }
}
