package com.lin.dao;

import com.lin.model.db.MetricEmergencyEvent;

import java.util.List;

/**
 * Created by Lin on 2019/10/28.
 */
public interface MetricEmergencyEventDao {

    /**
     * 查询没有关闭的告警事件
     * @param metricGid
     * @return
     */
    MetricEmergencyEvent selectUnSolvedEventByMetric(String metricGid);

    /**
     * 保存告警事件
     * @param metricEmergencyEvent
     * @return
     */
    int saveMetricEmergencyEvent(MetricEmergencyEvent metricEmergencyEvent);

    /**
     * 根据 gid和status 更新告警事件的异常数据
     * @param metricEmergencyEvent
     * @return
     */
    int updateAbnormalDataByGidAndStatus(MetricEmergencyEvent metricEmergencyEvent);

    /**
     * 查询已经关闭的告警事件
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
