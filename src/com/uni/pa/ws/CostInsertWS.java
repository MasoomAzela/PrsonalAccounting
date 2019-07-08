package com.uni.pa.ws;

import com.uni.pa.exception.DoesnotExistSubjectException;
import com.uni.pa.exception.DoesnotExistUserException;
import com.uni.pa.logic.CostInsertionBL;
import com.uni.pa.model.entity.CostTitle;
import com.uni.pa.model.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.uni.pa.constants.RequestAttributeConstants.errorFlag;

@WebServlet(name = "costInsertService", urlPatterns = "/costinsert")
public class CostInsertWS extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException,
            IOException {

        String doesnotExistUserMessage = "User doesn't exist.";
        String doesnotSubjectMessage = "Subject doesn't exist. Please say to dady add that.";

        String description = httpServletRequest.getParameter("description");
        String buyDateStr = httpServletRequest.getParameter("buyDate");
        long price = Long.parseLong(httpServletRequest.getParameter("price"));
        int subjectId = Integer.parseInt(httpServletRequest.getParameter("titleId"));
        int payerId = Integer.parseInt(httpServletRequest.getParameter("payerNameId"));
        int addedById = Integer.parseInt(httpServletRequest.getParameter("addedByNameId"));

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date buyDate = sdf.parse(buyDateStr);

            CostInsertionBL insertionBL = new CostInsertionBL();

            User payer = insertionBL.findPayer(payerId);
            User addedBy = insertionBL.findaddedBy(addedById);
            CostTitle subject = insertionBL.findSubject(subjectId);

            insertionBL.insert(subject, price, buyDate, payer, addedBy, description);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DoesnotExistUserException ex) {
            httpServletRequest.setAttribute(errorFlag, doesnotExistUserMessage);
            httpServletRequest.getRequestDispatcher("/users/insert").forward(httpServletRequest, httpServletResponse);
        } catch (DoesnotExistSubjectException ex) {
            httpServletRequest.setAttribute(errorFlag, doesnotSubjectMessage);
            httpServletRequest.getRequestDispatcher("/users/insert").forward(httpServletRequest, httpServletResponse);
        }
    }
}