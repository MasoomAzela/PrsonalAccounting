package com.uni.pa.ws;

import com.uni.pa.database.dao.AuthenticationDao;
import com.uni.pa.database.dao.UserDao;
import com.uni.pa.utility.DBProcess;
import com.uni.pa.model.entity.Authentication;
import com.uni.pa.model.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "startupService", urlPatterns = "/startup")
public class Startup extends HttpServlet {

    @Override
    public void init() {
        DBProcess dbProcess = new DBProcess();

        try {
            User user = new User();
            user.setfName("Ali");
            user.setlName("Alavi");
            user.setCredit(566867L);
            user.setSubject("Father");


            //new AuthenticationDao(new DBProcess()).findByUserName("admin");


            User attachedUser = new UserDao(dbProcess).saveOrUpdate(user);
            user.setId(attachedUser.getId());
            user.setSubject("***");

            Authentication auth = new Authentication();
            auth.setId(3);
            auth.setPassword("326604");
            auth.setActivationMode(true);
            auth.setSalt("seed");
            auth.setUser(user);
            auth.setUserName("al");

            AuthenticationDao authenticationDao = new AuthenticationDao(dbProcess);

//           authenticationDao.saveOrUpdate(auth);
dbProcess.getEntityManager().persist(auth);
//           RegistrationBL content = authenticationDao.findById(auth.getId());
//            List<RegistrationBL> cont = authenticationDao.finByUsername();

            dbProcess.commit();
        } catch (Exception e) {
            e.printStackTrace();
            dbProcess.rollback();
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendError(403);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Do post method called");
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Do delete method called");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy method called");
    }
}
