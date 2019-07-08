package com.uni.pa.ws;

import com.uni.pa.dto.AuthenticationRequestDto;
import com.uni.pa.exception.InvalidCredentialException;
import com.uni.pa.exception.InvalidUsernameException;
import com.uni.pa.exception.LockedUserException;
import com.uni.pa.exception.UserIsDeactiveException;
import com.uni.pa.logic.AuthenticationBL;
import com.uni.pa.model.entity.Authentication;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import static com.uni.pa.constants.RequestAttributeConstants.errorFlag;

@WebServlet(name = "loginService", urlPatterns = "/authenticate")
public class LoginWS extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException, ServletException {

        AuthenticationRequestDto requestDto = new AuthenticationRequestDto();
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");
        requestDto.setUsername(username);
        requestDto.setPassword(password);

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<AuthenticationRequestDto>> constraintViolations = validator.validate(requestDto);

        if (constraintViolations.size() != 0) {
            Iterator<ConstraintViolation<AuthenticationRequestDto>> iterator = constraintViolations.iterator();
            ConstraintViolation constraintViolation = iterator.next();
            httpServletRequest.setAttribute(errorFlag, constraintViolation.getMessage());
            httpServletRequest.getRequestDispatcher("/users/login").forward(httpServletRequest, httpServletResponse);
        }

        AuthenticationBL authenticationBL = new AuthenticationBL();
        HttpSession session = httpServletRequest.getSession();
        String authenticationFailedMessage = "Username or password is incorrect";
        String lockedUserMessage = "Username is locked";
        String inactiveUserMessage = "Username is inactive";

        try {
            authenticationBL.authentication(username, password);
            Authentication authentication = authenticationBL.fetchAuthInfo(username);
            session.setAttribute("authenticate", Boolean.TRUE);
            session.setAttribute("username", authentication.getUserName());
            if (session.getAttribute("requestURI") != null) {
                httpServletResponse.sendRedirect((String) session.getAttribute("requestURI"));
            } else {
                httpServletResponse.sendRedirect("/portal/dashboard");
            }
        } catch (InvalidCredentialException ex1) {
            httpServletRequest.setAttribute(errorFlag, authenticationFailedMessage);
            httpServletRequest.getRequestDispatcher("/users/login").forward(httpServletRequest, httpServletResponse);
        } catch (InvalidUsernameException ex2) {
            httpServletRequest.setAttribute(errorFlag, authenticationFailedMessage);
            httpServletRequest.getRequestDispatcher("/users/login").forward(httpServletRequest, httpServletResponse);
        } catch (LockedUserException ex3) {
            httpServletRequest.setAttribute(errorFlag, lockedUserMessage);
            httpServletRequest.getRequestDispatcher("/users/login").forward(httpServletRequest, httpServletResponse);
        } catch (UserIsDeactiveException ex4) {
            httpServletRequest.setAttribute(errorFlag, inactiveUserMessage);
            httpServletRequest.getRequestDispatcher("/users/login").forward(httpServletRequest, httpServletResponse);
        } catch (Exception ex5) {
            ex5.printStackTrace();
        }
    }
}