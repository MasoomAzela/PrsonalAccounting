package com.uni.pa.utility;


import com.uni.pa.model.entity.AbstractEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 * Created by Gladiator on 3/19/2016 AD.
 */
public class JpaUtil {
    private static JpaUtil instance = null;
    public EntityManagerFactory entityManagerFactory;

    private JpaUtil() {
        try {
            this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
        } catch (Exception ex) {
            ex.printStackTrace();
           // LogFactory.getLogger(this.getClass()).error("", ex);
        }
    }

    public static JpaUtil getInstance() {
        if (instance == null || instance.entityManagerFactory == null) {
            instance = new JpaUtil();
        }
        return instance;
    }

    public EntityManager getEntityManager() {
        try {
            return this.entityManagerFactory.createEntityManager();
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getMessage();
            //LogFactory.getLogger(this.getClass()).error("", ex);
            return null;
        }
    }

    public <T extends AbstractEntity> String getTableName(Class<T> entityClass) {
        Metamodel meta = entityManagerFactory.getMetamodel();
        EntityType<T> entityType = meta.entity(entityClass);

        //Check whether @Table annotation is present on the class.
        Table t = entityClass.getAnnotation(Table.class);

        return (t == null)
                ? entityType.getName().toUpperCase()
                : t.name();
    }
}

