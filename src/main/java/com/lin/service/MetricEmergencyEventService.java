package com.lin.service;

import com.lin.model.db.LogEventAbnormalData;
import com.lin.model.db.LogEventStatus;
import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;

import java.util.List;

/**
 * Created by Lin on 2019/10/28.
 */
public interface MetricEmergencyEventService {

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

    /**
     * 保存新的告警事件
     * @param newEvent
     * @param logEventStatus
     * @param logEventAbnormalData
     * @return
     */
    boolean saveNewEmergencyEvent(MetricEmergencyEvent newEvent, LogEventStatus logEventStatus, LogEventAbnormalData logEventAbnormalData);

    /**
     * 更新告警事件
     *
     * @param updateEvent
     * @param logEventStatus
     * @param logEventAbnormalData
     * @return
     */
    boolean updateAbnormalData(MetricEmergencyEvent updateEvent, MetricEmergencyEvent oldEvent, LogEventStatus logEventStatus, LogEventAbnormalData logEventAbnormalData);

}
