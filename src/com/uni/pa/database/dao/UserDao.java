package com.uni.pa.database.dao;

import com.uni.pa.utility.DBProcess;
import com.uni.pa.model.entity.User;

public class UserDao extends BaseDao<User> {

    public UserDao(DBProcess dbProcess) {
        super(dbProcess);
    }

}
