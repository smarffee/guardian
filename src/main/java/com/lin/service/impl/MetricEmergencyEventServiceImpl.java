package com.lin.service.impl;

import com.lin.dao.LogEventAbnormalDataDao;
import com.lin.dao.LogEventStatusDao;
import com.lin.dao.MetricEmergencyEventDao;
import com.lin.model.db.LogEventAbnormalData;
import com.lin.model.db.LogEventStatus;
import com.lin.model.db.MetricEmergencyEvent;
import com.lin.service.MetricEmergencyEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Lin on 2019/10/28.
 */
@Service
public class MetricEmergencyEventServiceImpl implements MetricEmergencyEventService {

    private static final Logger logger = LoggerFactory.getLogger(MetricEmergencyEventServiceImpl.class);

    @Autowired
    private MetricEmergencyEventDao metricEmergencyEventDao;

    @Autowired
    private LogEventStatusDao logEventStatusDao;

    @Autowired
    private LogEventAbnormalDataDao logEventAbnormalDataDao;

    /**
     * 更新告警事件状态
     * @param updateEvent
     * @param oldEvent
     * @param logEventStatus
     * @param logEventAbnormalData
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateAbnormalData(MetricEmergencyEvent updateEvent, MetricEmergencyEvent oldEvent, LogEventStatus logEventStatus, LogEventAbnormalData logEventAbnormalData) {
        int num = metricEmergencyEventDao.updateAbnormalData(updateEvent, oldEvent);
        if (num == 1) {
            if (logEventStatus != null) {
                logEventStatusDao.saveLogEventStatus(logEventStatus);
            }
            if (logEventAbnormalData != null) {
                logEventAbnormalDataDao.saveLogEventAbnormalData(logEventAbnormalData);
            }
            return true;
        }

        return false;
    }

    /**
     * 保存新的告警事件
     *
     * @param newEvent
     * @param logEventStatus
     * @param logEventAbnormalData
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveNewEmergencyEvent(MetricEmergencyEvent newEvent, LogEventStatus logEventStatus, LogEventAbnormalData logEventAbnormalData) {
        int num = metricEmergencyEventDao.saveMetricEmergencyEvent(newEvent);

        if (num == 1) {
            logEventStatusDao.saveLogEventStatus(logEventStatus);
            logEventAbnormalDataDao.saveLogEventAbnormalData(logEventAbnormalData);
            return true;
        }

        return false;
    }

    @Override
    public MetricEmergencyEvent selectUnSolvedEventByMetric(String metricGid) {
        return metricEmergencyEventDao.selectUnSolvedEventByMetric(metricGid);
    }

    @Override
    public List<MetricEmergencyEvent> selectSolvedEvent() {
        return metricEmergencyEventDao.selectSolvedEvent();
    }

    @Override
    public int deleteByGid(String gid) {
        return metricEmergencyEventDao.deleteByGid(gid);
    }

}
