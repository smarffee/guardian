package com.lin.dao.impl;

import com.lin.dao.LogEventAbnormalDataDao;
import com.lin.dao.mapper.guardian.LogEventAbnormalDataMapper;
import com.lin.model.db.LogEventAbnormalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogEventAbnormalDataDaoImpl implements LogEventAbnormalDataDao {

    @Autowired
    private LogEventAbnormalDataMapper logEventAbnormalDataMapper;

    @Override
    public int saveLogEventAbnormalData(LogEventAbnormalData logEventAbnormalData) {
        return 0;
    }

}
