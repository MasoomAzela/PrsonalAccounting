package com.uni.pa.database.dao;

import com.uni.pa.utility.DBProcess;
import com.uni.pa.model.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {

    private DBProcess dbProcess;
    private final Class<T> aClass;


    public BaseDao(DBProcess dbProcess) {
        this.dbProcess = dbProcess;
        this.aClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public DBProcess getDbProcess() {
        return dbProcess;
    }

    public void setDbProcess(DBProcess dbProcess) {
        this.dbProcess = dbProcess;
    }

    public T findById(int id) {
        try {
            T result = getDbProcess().getEntityManager().find(this.aClass, id);
            getDbProcess().getEntityManager().detach(result);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<T> findAll() {
        try {
            EntityManager entityManager = getDbProcess().getEntityManager();
            Query query = entityManager.createQuery("SELECT t from " + this.aClass.getSimpleName() + " t");
            List<T> listResults = query.getResultList();
            listResults.forEach(item -> getDbProcess().getEntityManager().detach(item));
            return listResults;
        } catch (Exception e) {
            throw e;
        }
    }

    public T saveOrUpdate(T entityClass) {
        try {
            T result = getDbProcess().getEntityManager().merge(entityClass);
            entityClass.setId(result.getId());
            return result;
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(T entityclass) {
        try {
            getDbProcess().getEntityManager().remove(entityclass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
