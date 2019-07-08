package com.uni.pa.ws;

import com.uni.pa.model.entity.Authentication;
import com.uni.pa.model.entity.User;
import com.uni.pa.logic.RegistrationBL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(name = "registrationService", urlPatterns = "/registration")
public class RegistrationWS extends HttpServlet {

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String fName = httpServletRequest.getParameter("f_Name");
        String lName = httpServletRequest.getParameter("l_Name");
        String subject = httpServletRequest.getParameter("subject");
        long credit = Long.parseLong(httpServletRequest.getParameter("credit"));
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        RegistrationBL registrationBL = new RegistrationBL();
        User user = registrationBL.enrollment(fName, lName, subject, credit);
        try {
            Authentication auth = registrationBL.identityRegistration(username, password, user);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        httpServletResponse.getWriter().write("User added successfully");
    }
}