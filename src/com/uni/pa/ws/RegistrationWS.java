package com.uni.pa.ws;

import com.uni.pa.dto.AuthenticationRequestDto;
import com.uni.pa.dto.RegistrationDto;
import com.uni.pa.exception.DoesnotExistRoleException;
import com.uni.pa.exception.DoesnotExistUserException;
import com.uni.pa.logic.RegistrationBL;
import com.uni.pa.model.entity.Authentication;
import com.uni.pa.model.entity.Role;
import com.uni.pa.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Set;

import static com.uni.pa.constants.RequestAttributeConstants.errorFlag;

@WebServlet(name = "registrationService", urlPatterns = "/registration")
public class RegistrationWS extends HttpServlet {

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        String fName = httpServletRequest.getParameter("f_Name");
        String lName = httpServletRequest.getParameter("l_Name");
        String subject = httpServletRequest.getParameter("subject");
        long credit = Long.parseLong(httpServletRequest.getParameter("credit"));
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        int roleId = Integer.parseInt(httpServletRequest.getParameter("roleId"));

        RegistrationDto registrationDto = new RegistrationDto();
        registrationDto.setfName(fName);
        registrationDto.setlName(lName);
        registrationDto.setSubject(subject);
        registrationDto.setCredit(credit);

        AuthenticationRequestDto authenticationRequestDto = new AuthenticationRequestDto();
        authenticationRequestDto.setUsername(username);
        authenticationRequestDto.setPassword(password);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<RegistrationDto>> regConstraintViolations = validator.validate(registrationDto);
        Set<ConstraintViolation<AuthenticationRequestDto>> authConstraintViolations = validator.validate(authenticationRequestDto);

        if (regConstraintViolations.size() != 0) {
            Iterator<ConstraintViolation<RegistrationDto>> iterator = regConstraintViolations.iterator();
            ConstraintViolation constraintViolation = iterator.next();
            httpServletRequest.setAttribute(errorFlag, constraintViolation.getMessage());
            httpServletRequest.getRequestDispatcher("/portal/registration").forward(httpServletRequest, httpServletResponse);
        }

        if (authConstraintViolations.size() != 0) {
            Iterator<ConstraintViolation<AuthenticationRequestDto>> iterator = authConstraintViolations.iterator();
            ConstraintViolation constraintViolation = iterator.next();
            httpServletRequest.setAttribute(errorFlag, constraintViolation.getMessage());
            httpServletRequest.getRequestDispatcher("/portal/registration").forward(httpServletRequest, httpServletResponse);
        }

        RegistrationBL registrationBL = new RegistrationBL();
        try {
            User user = registrationBL.enrollment(fName, lName, subject, credit);
            Role role = registrationBL.findRole(roleId);
            Authentication auth = registrationBL.identityRegistration(username, password, user, role);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (DoesnotExistRoleException e) {
            e.printStackTrace();
        } catch (DoesnotExistUserException e) {
            e.printStackTrace();
        }
        httpServletResponse.getWriter().write("User added successfully");
    }
}