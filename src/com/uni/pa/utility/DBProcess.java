package com.uni.pa.utility;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * Created by Gladiator on 4/29/2016 AD.
 */
public class DBProcess {
    private EntityManager entityManager;
    private EntityTransaction transaction;
    private boolean wasRollbacked = false;

    public DBProcess() {
        this.entityManager = JpaUtil.getInstance().getEntityManager();
        this.transaction = this.entityManager.getTransaction();
        this.transaction.begin();
    }

    public DBProcess(EntityManager entityManager, EntityTransaction transaction) {
        this.entityManager = entityManager;
        this.transaction = transaction;
        this.transaction.begin();
    }

    public DBProcess(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.transaction = entityManager.getTransaction();
        this.transaction.begin();
    }

    public void commit() {
        if (!wasRollbacked) {
            this.transaction.commit();
        }
        if (this.entityManager.isOpen()) {
            this.entityManager.close();
        }
    }

    public void rollback() {
        this.transaction.rollback();
        this.wasRollbacked = true;
        if (this.entityManager.isOpen()) {
            this.entityManager.close();
        }
    }

    public EntityManager getEntityManager() {

        return this.entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityTransaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(EntityTransaction transaction) {
        this.transaction = transaction;
    }
}