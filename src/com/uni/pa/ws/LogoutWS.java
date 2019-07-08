package com.uni.pa.ws;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logoutService", urlPatterns = "/portal/logout")
public class LogoutWS extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/portal/dashboard.jsp");
    }
}
