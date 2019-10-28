package com.lin.service;

import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;

import java.util.List;

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
     * 根据监控项gid查询正在告警事件
     * @param metricGid
     * @return
     */
    MetricEmergencyEvent selectUnSolvedEventByMetric(String metricGid);

    /**
     * 根据监控项gid查询已经关闭的告警事件
     *
     * @return
     */
    List<MetricEmergencyEvent> selectSolvedEvent();

    /**
     * 根据gid删除记录
     * @param gid
     * @return
     */
    int deleteByGid(String gid);
}
