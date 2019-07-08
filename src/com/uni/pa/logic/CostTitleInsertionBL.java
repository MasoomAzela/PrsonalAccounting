package com.uni.pa.logic;

import com.uni.pa.database.dao.CostTitleDao;
import com.uni.pa.model.entity.CostTitle;
import com.uni.pa.utility.DBProcess;

public class CostTitleInsertionBL {

    public CostTitle costTitleInsert(String name, String description) {
        DBProcess dbProcess = new DBProcess();
        CostTitleDao costTitleDao = new CostTitleDao(dbProcess);
        CostTitle entry = costTitleDao.saveOrUpdate(new CostTitle(name, description));
        dbProcess.commit();
        return entry;
    }
}
