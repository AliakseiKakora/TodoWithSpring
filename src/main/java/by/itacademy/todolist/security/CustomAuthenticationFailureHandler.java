package by.itacademy.todolist.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {

        String contextPath = httpServletRequest.getServletContext().getContextPath();

        if (e instanceof BadCredentialsException) {
            httpServletResponse.sendRedirect(contextPath + "/login?error=true");
        } else {
            String login = httpServletRequest.getParameter("login");
            httpServletRequest.getSession().setAttribute("userLogin", login);
            httpServletResponse.sendRedirect(contextPath + "/block");
        }
    }
}
