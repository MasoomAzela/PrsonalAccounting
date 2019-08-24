package com.uni.pa.ws;

import com.uni.pa.exception.DoesnotExistSubjectException;
import com.uni.pa.exception.DoesnotExistUserException;
import com.uni.pa.logic.CostInsertionBL;
import com.uni.pa.model.entity.CostTitle;
import com.uni.pa.model.entity.User;
import com.uni.pa.utility.DateConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
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
        try {
            String description = httpServletRequest.getParameter("description");
            String buyYearStr = httpServletRequest.getParameter("buyYear");
            String buyMonthStr = httpServletRequest.getParameter("buyMonth");
            String buyDayStr = httpServletRequest.getParameter("buyDay");
            long price = Long.parseLong(httpServletRequest.getParameter("price"));
            int subjectId = Integer.parseInt(httpServletRequest.getParameter("titleId"));
            int payerId = Integer.parseInt(httpServletRequest.getParameter("payerNameId"));
            int addedById = Integer.parseInt(httpServletRequest.getParameter("addedByNameId"));
            int buyYear = Integer.parseInt(buyYearStr);
            int buyMonth = Integer.parseInt(buyMonthStr);
            int buyDay = Integer.parseInt(buyDayStr);

            int[] date = DateConverter.jalali_to_gregorian(buyYear, buyMonth, buyDay);

           StringBuilder str = new StringBuilder();
           for(int i : date)
               str = str.append(i);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date buyDate = sdf.parse(str.toString());
            Timestamp ts = new Timestamp(buyDate.getTime());

            CostInsertionBL costInsertionBL = new CostInsertionBL();

            User payer = costInsertionBL.findPayer(payerId);
            User addedBy = costInsertionBL.findaddedBy(addedById);
            CostTitle subject = costInsertionBL.findSubject(subjectId);

            costInsertionBL.insert(subject, price, ts, payer, addedBy, description);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (DoesnotExistUserException ex) {
            httpServletRequest.setAttribute(errorFlag, doesnotExistUserMessage);
            httpServletRequest.getRequestDispatcher("/portal/registration").forward(httpServletRequest, httpServletResponse);
        } catch (DoesnotExistSubjectException ex) {
            httpServletRequest.setAttribute(errorFlag, doesnotSubjectMessage);
            httpServletRequest.getRequestDispatcher("/costtitleinsert").forward(httpServletRequest, httpServletResponse);
        }
    }
}