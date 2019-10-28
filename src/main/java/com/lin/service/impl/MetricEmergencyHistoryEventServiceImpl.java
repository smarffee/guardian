package com.lin.service.impl;

import com.lin.dao.MetricEmergencyHistoryEventDao;
import com.lin.service.MetricEmergencyHistoryEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricEmergencyHistoryEventServiceImpl implements MetricEmergencyHistoryEventService {

    @Autowired
    private MetricEmergencyHistoryEventDao metricEmergencyHistoryEventDao;



}
