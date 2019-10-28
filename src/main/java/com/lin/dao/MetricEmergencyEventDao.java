package com.lin.dao;

import com.lin.model.db.MetricEmergencyEvent;

/**
 * Created by Lin on 2019/10/28.
 */
public interface MetricEmergencyEventDao {

    MetricEmergencyEvent selectUnSolvedEventByMetric(String metricGid);

    int saveMetricEmergencyEvent(MetricEmergencyEvent metricEmergencyEvent);

    int updateEventAbnormalDataByGid(MetricEmergencyEvent metricEmergencyEvent);
}
