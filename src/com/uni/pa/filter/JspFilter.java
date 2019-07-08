package com.uni.pa.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"*.jsp"})
public class JspFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        /*if (request.getRequestURI().contains("/login.jsp")) {
            chain.doFilter(servletRequest, servletResponse);
        } else {*/
            response.sendError(404);
     //   }
    }

    public void destroy() {
    }
}
