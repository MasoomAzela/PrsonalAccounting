package com.uni.pa.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/portal/*"})
public class AuthenticatorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
      /*  boolean isLoginURI = request.getRequestURI().startsWith("/portal");
        boolean isLoginPage = request.getRequestURI().endsWith("/login.jsp");*/

        if (session != null && session.getAttribute("authenticate") != null) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        String requestURI = request.getRequestURI();
        session.setAttribute("requestURI", requestURI);

        response.sendRedirect("/users/login");
    }

    public void destroy() {
    }
}