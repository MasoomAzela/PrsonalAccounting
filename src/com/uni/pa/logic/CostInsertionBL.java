package com.uni.pa.logic;

import com.uni.pa.database.dao.CostDao;
import com.uni.pa.database.dao.CostTitleDao;
import com.uni.pa.database.dao.UserDao;
import com.uni.pa.exception.DoesnotExistSubjectException;
import com.uni.pa.exception.DoesnotExistUserException;
import com.uni.pa.model.entity.Cost;
import com.uni.pa.model.entity.CostTitle;
import com.uni.pa.model.entity.User;
import com.uni.pa.utility.DBProcess;

import java.util.Date;

public class CostInsertionBL extends BaseBL {

    public CostTitle findSubject(int subjectId) throws DoesnotExistUserException, DoesnotExistSubjectException {

        DBProcess dbProcess = new DBProcess();

        CostTitle subject = new CostTitleDao(dbProcess).findById(subjectId);

        if (subject == null) {
            throw new DoesnotExistSubjectException();
        }

        return subject;
    }

    public User findPayer(int payerId) throws DoesnotExistUserException {

        DBProcess dbProcess = new DBProcess();

        User payer = new UserDao(dbProcess).findById(payerId);

        if (payer == null) {
            throw new DoesnotExistUserException();
        }

        return payer;
    }

    public User findaddedBy(int addedById) throws DoesnotExistUserException {

        DBProcess dbProcess = new DBProcess();

        User addedBy = new UserDao(dbProcess).findById(addedById);

        if (addedBy == null) {
            throw new DoesnotExistUserException();
        }
        return addedBy;
    }

    public void insert(CostTitle subject, long price, Date buyDate, User payer, User addedBy, String description) {

        DBProcess dbProcess = new DBProcess();
        CostDao costDao = new CostDao(dbProcess);
        Cost insert = costDao.saveOrUpdate(new Cost(subject, price, buyDate, payer, addedBy, description));
        dbProcess.commit();
    }
}