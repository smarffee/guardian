package com.lin.dao;

import com.lin.model.db.MetricEmergencyHistoryEvent;

public interface MetricEmergencyHistoryEventDao {

    int saveHistoryEvent(MetricEmergencyHistoryEvent historyEvent);

}
