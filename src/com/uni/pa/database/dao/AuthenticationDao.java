package com.uni.pa.database.dao;

import com.uni.pa.utility.DBProcess;
import com.uni.pa.model.entity.Authentication;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class AuthenticationDao extends BaseDao<Authentication> {

    public AuthenticationDao(DBProcess dbProcess) {
        super(dbProcess);
    }

    public Authentication finByUsername(String username) {
        try {
            EntityManager entityManager = getDbProcess().getEntityManager();
            Query query = entityManager.createQuery("SELECT t from Authentication t where t.userName = :username");
            query.setParameter("username", username);
            Authentication result = (Authentication) query.getSingleResult();
            getDbProcess().getEntityManager().detach(result);
            return result;
        } catch (NoResultException e) {
            return null;
        }
    }
}
