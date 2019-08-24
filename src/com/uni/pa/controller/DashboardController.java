package com.uni.pa.controller;

import com.uni.pa.logic.AuthenticationBL;
import com.uni.pa.model.entity.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "dashboardController", urlPatterns = "/portal/dashboard")
public class DashboardController extends BaseController {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();

        String username = (String) httpSession.getAttribute("username");

        Authentication authInfo = new AuthenticationBL().fetchAuthInfo(username);
        String fullName = authInfo.getUser().getFullName();
        req.setAttribute("f", fullName);
        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }
}