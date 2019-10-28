package com.lin.dao.impl;

import com.lin.dao.MetricEmergencyHistoryEventDao;
import com.lin.dao.mapper.guardian.MetricEmergencyHistoryEventMapper;
import com.lin.model.db.MetricEmergencyHistoryEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MetricEmergencyHistoryEventDaoImpl implements MetricEmergencyHistoryEventDao {

    @Autowired
    private MetricEmergencyHistoryEventMapper metricEmergencyHistoryEventMapper;

    @Override
    public int saveHistoryEvent(MetricEmergencyHistoryEvent historyEvent) {
        return metricEmergencyHistoryEventMapper.insert(historyEvent);
    }

}
