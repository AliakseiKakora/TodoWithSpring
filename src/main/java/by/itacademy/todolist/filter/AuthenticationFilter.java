package by.itacademy.todolist.filter;

import by.itacademy.todolist.constants.ApplicationConstants;
import by.itacademy.todolist.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@WebFilter (urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class AuthenticationFilter implements Filter {


    private String [] guestPages = {"/index.jsp", "/login.jsp", "/registration.jsp", "/blocked.jsp", "/guest"};
    private String [] adminPages = {"/adminPage.jsp", "/allUsers.jsp"};

    List<String> guestPagesList = new ArrayList<>(Arrays.asList(guestPages));
    List<String> adminPagesList = new ArrayList<>(Arrays.asList(adminPages));

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String url = req.getServletPath();

        User user = null;

        if (session != null) {
            user = (User) session.getAttribute(ApplicationConstants.USER_KEY);
        }

        if (user != null && !user.getProfile().isEnable()) {
            session.invalidate();
            req.setAttribute(ApplicationConstants.USER_ID_KEY, user.getId());
            req.getRequestDispatcher("/guest?command=BlockedView").forward(request, response);
            return;
        }

        if (user != null && adminPagesList.contains(url) && !isAdmin(user)) {
            res.sendRedirect("/security.jsp");
            return;
        }

        if ((session == null || session.getAttribute(ApplicationConstants.USER_KEY) == null) && guestPagesList.contains(url)) {
            chain.doFilter(request, response);
            return;
        }

        if (session != null && session.getAttribute(ApplicationConstants.USER_KEY) != null) {
            chain.doFilter(request, response);
        } else {
            String context = req.getContextPath();
            res.sendRedirect(context + "/index.jsp");
        }

     }

    @Override
    public void destroy() {

    }

    private boolean isAdmin(User user) {
        return user.getRoles().stream().anyMatch(role -> role.getRole().equals(ApplicationConstants.ROLE_ADMIN_KEY));
    }

}