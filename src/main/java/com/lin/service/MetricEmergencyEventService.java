package com.lin.service;

import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;

/**
 * Created by Lin on 2019/10/28.
 */
public interface MetricEmergencyEventService {

    /**
     * 告警事件状态发生改变
     *
     * @param currentEvent 当前告警事件
     * @param metricItem 监控项
     * @param metricStatusRequest 异常数据请求
     */
    void emergencyEventChanged(MetricEmergencyEvent currentEvent, MetricItem metricItem, MetricStatusRequest metricStatusRequest);

    /**
     * 根据监控项key查询正在告警事件
     * @param metricKey
     * @return
     */
    MetricEmergencyEvent selectUnSolvedEventByMetric(String metricKey);
}
