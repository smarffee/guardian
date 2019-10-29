package com.lin.dao.impl;

import com.lin.dao.LogEventStatusDao;
import com.lin.dao.mapper.guardian.LogEventStatusMapper;
import com.lin.model.db.LogEventStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LogEventStatusDaoImpl implements LogEventStatusDao {

    @Autowired
    private LogEventStatusMapper logEventStatusMapper;

    @Override
    public int saveLogEventStatus(LogEventStatus logEventStatus) {
        return logEventStatusMapper.insert(logEventStatus);
    }

}
