package com.uni.pa.ws;

import com.uni.pa.logic.CostTitleInsertionBL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "insertService", urlPatterns = "/costtitleinsert")
public class CostTitleInsertWS extends HttpServlet {

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        String description = httpServletRequest.getParameter("description");
        String name = httpServletRequest.getParameter("name");

        CostTitleInsertionBL cti = new CostTitleInsertionBL();
        cti.costTitleInsert(name, description);
    }
}