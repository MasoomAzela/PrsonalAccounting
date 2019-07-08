package com.uni.pa.ws;

import com.uni.pa.logic.AddRoleBL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "addRoleService", urlPatterns = "/addrole")
public class AddRoleWS extends HttpServlet {
    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        try {

            String description = httpServletRequest.getParameter("description");
            String name = httpServletRequest.getParameter("name");

            Map<String, String[]> m = httpServletRequest.getParameterMap();

            Set s = m.entrySet();
            Iterator it = s.iterator();
            String[] permissions = null;

            while (it.hasNext()) {
                Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) it.next();
                String key = entry.getKey();
                permissions = entry.getValue();
            }

            AddRoleBL addRoleBL = new AddRoleBL();
            addRoleBL.roleInsert(name, description, permissions);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}