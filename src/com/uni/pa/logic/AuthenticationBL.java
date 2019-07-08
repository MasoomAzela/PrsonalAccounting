package com.uni.pa.logic;

import com.uni.pa.database.dao.AuthenticationDao;
import com.uni.pa.exception.InvalidCredentialException;
import com.uni.pa.exception.InvalidUsernameException;
import com.uni.pa.exception.LockedUserException;
import com.uni.pa.exception.UserIsDeactiveException;
import com.uni.pa.model.entity.Authentication;
import com.uni.pa.utility.DBProcess;

import java.security.NoSuchAlgorithmException;

public class AuthenticationBL extends BaseBL {

    public AuthenticationBL() {
    }

    public Authentication fetchAuthInfo(String username) {
        DBProcess dbProcess = new DBProcess();
        try {
            AuthenticationDao authDao = new AuthenticationDao(dbProcess);
            Authentication authentication = authDao.finByUsername(username);
            return authentication;
        } catch (Exception e) {
            e.printStackTrace();
            dbProcess.rollback();
            throw e;
        } finally {
            dbProcess.commit();
        }
    }

    public void authentication(String username, String password) throws InvalidCredentialException,
            NoSuchAlgorithmException, LockedUserException, UserIsDeactiveException, InvalidUsernameException {

        Authentication authentication = fetchAuthInfo(username);

        if (authentication == null) {
            throw new InvalidCredentialException();
        }
        boolean result = authentication.validatePassword(password);

        boolean activationMode = authentication.isActivationMode();
        if (!activationMode) {
            throw new UserIsDeactiveException();
        }

        if (!result) {
            throw new InvalidCredentialException();
        }
    }
}
