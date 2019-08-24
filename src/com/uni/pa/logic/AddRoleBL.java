package com.uni.pa.logic;

import com.uni.pa.assets.PermissionParam;
import com.uni.pa.database.dao.RoleDao;
import com.uni.pa.model.entity.Role;
import com.uni.pa.utility.DBProcess;

import java.util.List;

public class AddRoleBL {
    public Role roleInsert(String name, String description, List<PermissionParam> permissions) {
        DBProcess dbProcess = new DBProcess();
        RoleDao roleDao = new RoleDao(dbProcess);
        Role entry = roleDao.saveOrUpdate(new Role(name, description, permissions));
        dbProcess.commit();
        return entry;
    }
}