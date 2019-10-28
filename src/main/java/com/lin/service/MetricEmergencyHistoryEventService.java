package com.lin.service;

import com.lin.model.db.MetricEmergencyHistoryEvent;

public interface MetricEmergencyHistoryEventService {

    int saveHistoryEvent(MetricEmergencyHistoryEvent historyEvent);

}
