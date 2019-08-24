package com.uni.pa.logic;

import com.uni.pa.database.dao.AuthenticationDao;
import com.uni.pa.database.dao.RoleDao;
import com.uni.pa.database.dao.UserDao;
import com.uni.pa.exception.DoesnotExistRoleException;
import com.uni.pa.exception.DoesnotExistUserException;
import com.uni.pa.model.entity.Authentication;
import com.uni.pa.model.entity.Role;
import com.uni.pa.model.entity.User;
import com.uni.pa.model.entity.UserRole;
import com.uni.pa.utility.DBProcess;

import java.security.NoSuchAlgorithmException;

public class RegistrationBL extends BaseBL {

    public Role findRole(int roleId) throws DoesnotExistRoleException, DoesnotExistUserException {

        DBProcess dbProcess = new DBProcess();

        Role role = new RoleDao(dbProcess).findById(roleId);

        if (role == null) {
            throw new DoesnotExistRoleException();
        }

        return role;
    }

    public User enrollment(String fName, String lName, String subject, long credit) {
        DBProcess dbProcess = new DBProcess();
        UserDao userDao = new UserDao(dbProcess);
        User registration = userDao.saveOrUpdate(new User(fName, lName, subject, credit));
        dbProcess.commit();
        return registration;
    }

    public Authentication identityRegistration(String userName, String password, User user, Role role) throws
            NoSuchAlgorithmException {
        DBProcess dbProcess = new DBProcess();
        AuthenticationDao authDao = new AuthenticationDao(dbProcess);
        Authentication authentication = new Authentication(userName, password);
        UserRole userRole = new UserRole();
        authentication.changePassword(password);
        password = authentication.getPassword();
        String salt = authentication.getSalt();
        authentication.setUser(user);
        userRole.setRole(role);
        authDao.saveOrUpdate(authentication);
        dbProcess.commit();
        return authentication;
    }
}