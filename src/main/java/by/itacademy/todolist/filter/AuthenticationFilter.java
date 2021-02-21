package by.itacademy.todolist.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter (urlPatterns = {"/main.jsp", "/profile.jsp"})
public class AuthenticationFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        if (session != null && (session.getAttribute("user") != null)) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/login.jsp");
        }
     }

    @Override
    public void destroy() {

    }
}
