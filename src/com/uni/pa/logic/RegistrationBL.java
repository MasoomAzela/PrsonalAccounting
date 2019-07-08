package com.uni.pa.logic;

import com.uni.pa.database.dao.AuthenticationDao;
import com.uni.pa.database.dao.UserDao;
import com.uni.pa.model.entity.Authentication;
import com.uni.pa.model.entity.User;
import com.uni.pa.utility.DBProcess;

import java.security.NoSuchAlgorithmException;

public class RegistrationBL extends BaseBL {

    public User enrollment(String fName, String lName, String subject, long credit) {
        DBProcess dbProcess = new DBProcess();
        UserDao userDao = new UserDao(dbProcess);
        User registration = userDao.saveOrUpdate(new User(fName, lName, subject, credit));
        dbProcess.commit();
        return registration;
    }

    public Authentication identityRegistration(String userName, String password, User user) throws NoSuchAlgorithmException {
        DBProcess dbProcess = new DBProcess();
        AuthenticationDao authDao = new AuthenticationDao(dbProcess);
        Authentication authentication = new Authentication(userName, password);
        authentication.changePassword(password);
        password = authentication.getPassword();
        String salt = authentication.getSalt();
        authentication.setUser(user);
        authDao.saveOrUpdate(authentication);
        dbProcess.commit();
        return authentication;
    }
}