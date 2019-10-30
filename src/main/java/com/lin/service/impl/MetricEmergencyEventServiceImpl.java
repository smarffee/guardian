package com.lin.service.impl;

import com.google.gson.Gson;
import com.lin.dao.LogEventAbnormalDataDao;
import com.lin.dao.LogEventStatusDao;
import com.lin.dao.MetricEmergencyEventDao;
import com.lin.model.AbnormalData;
import com.lin.model.db.*;
import com.lin.model.event.EmergencyEventListResponse;
import com.lin.service.MetricEmergencyEventService;
import com.lin.service.MetricGroupService;
import com.lin.service.MetricItemService;
import com.lin.service.MetricSubGroupService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private MetricGroupService metricGroupService;

    @Autowired
    private MetricSubGroupService metricSubGroupService;

    @Autowired
    private MetricItemService metricItemService;

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
    public EmergencyEventListResponse selectAllUnSolvedEvent() {
        List<MetricEmergencyEvent> emergencyEventList = metricEmergencyEventDao.selectAllUnSolvedEvent();

        if (CollectionUtils.isEmpty(emergencyEventList)) {
            logger.info("selectAllUnSolvedEvent: there is no unsolved emergency event.");
            return null;
        }

        List<String> metricGid = new ArrayList<>();
        List<String> groupGidList = new ArrayList<>();
        List<String> subGroupGidList = new ArrayList<>();
        for (MetricEmergencyEvent emergencyEvent : emergencyEventList) {
            metricGid.add(emergencyEvent.getMetricGid());
            groupGidList.add(emergencyEvent.getGroupGid());
            subGroupGidList.add(emergencyEvent.getSubGroupGid());
        }

        Map<String, MetricItem> metricItemMap = new HashMap<>();
        for (MetricItem metricItem : metricItemService.selectByMetricGid(metricGid)) {
            metricItemMap.put(metricItem.getGid(), metricItem);
        }

        Map<String, MetricGroup> metricGroupMap = new HashMap<>();
        for (MetricGroup metricGroup : metricGroupService.selectGroupByGid(groupGidList)) {
            metricGroupMap.put(metricGroup.getGid(), metricGroup);
        }

        Map<String, MetricSubGroup> metricSubGroupMap = new HashMap<>();
        for (MetricSubGroup metricSubGroup : metricSubGroupService.selectSubGroupByGid(subGroupGidList)) {
            metricSubGroupMap.put(metricSubGroup.getGid(), metricSubGroup);
        }

        List<EmergencyEventListResponse.EmergencyEvent> eventList = new ArrayList<>();

        for (MetricEmergencyEvent emergencyEvent : emergencyEventList) {
            EmergencyEventListResponse.EmergencyEvent event = new EmergencyEventListResponse.EmergencyEvent();

            MetricItem metricItem = metricItemMap.get(emergencyEvent.getMetricGid());
            MetricGroup metricGroup = metricGroupMap.get(emergencyEvent.getGroupGid());
            MetricSubGroup metricSubGroup = metricSubGroupMap.get(emergencyEvent.getSubGroupGid());

            event.setEventGid(emergencyEvent.getGid());
            event.setMetricGid(metricItem.getGid());
            event.setMetricKey(metricItem.getMetricKey());
            event.setMetricName(metricItem.getName());
            event.setGroupGid(metricGroup.getGid());
            event.setGroupName(metricGroup.getGroupName());
            event.setSubGroupGid(metricSubGroup.getGid());
            event.setSubGroupName(metricSubGroup.getSubGroupName());
            event.setStartTime(emergencyEvent.getCreateTime());
            event.setLevel(emergencyEvent.getLevel());
            event.setStatus(emergencyEvent.getStatus());
            event.setDesc(emergencyEvent.getDescription());

            AbnormalData abnormalData = new Gson().fromJson(emergencyEvent.getAbnormalData(), AbnormalData.class);
            event.setAbnormalData(abnormalData);

            eventList.add(event);
        }

        EmergencyEventListResponse response = new EmergencyEventListResponse();

        response.setEventList(eventList);

        return response;
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
