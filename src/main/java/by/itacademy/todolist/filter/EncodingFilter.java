package by.itacademy.todolist.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private String encoding = "utf-8";

    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    public void destroy() {

    }
}