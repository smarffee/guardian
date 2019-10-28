package com.lin.service.impl;

import com.google.gson.Gson;
import com.lin.dao.LogEventAbnormalDataDao;
import com.lin.dao.LogEventStatusDao;
import com.lin.dao.MetricEmergencyEventDao;
import com.lin.model.MQExchangeEnum;
import com.lin.model.QueueMessage;
import com.lin.model.db.LogEventAbnormalData;
import com.lin.model.db.LogEventStatus;
import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.MetricEmergencyEventService;
import com.lin.util.Constant;
import com.lin.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lin on 2019/10/28.
 */
@Service
public class MetricEmergencyEventServiceImpl implements MetricEmergencyEventService {

    private static final Logger logger = LoggerFactory.getLogger(MetricEmergencyEventServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MetricEmergencyEventDao metricEmergencyEventDao;

    @Autowired
    private LogEventStatusDao logEventStatusDao;

    @Autowired
    private LogEventAbnormalDataDao logEventAbnormalDataDao;

    @Override
    public void emergencyEventChanged(MetricEmergencyEvent currentEmergencyEvent, MetricItem metricItem, MetricStatusRequest metricStatusRequest) {

        if (currentEmergencyEvent == null) {
            //如果预警项当前没有告警, 说明产生了一个新的告警
            currentEmergencyEvent = happenNewEmergencyEvent(metricItem, metricStatusRequest);
        } else {
            //如果预警项正在告警, 说明告警状态发生变更
            emergencyEventStatusChange(currentEmergencyEvent, metricStatusRequest);
        }

        //发送告警信息
        sendAlarmMessage(currentEmergencyEvent);
    }

    public void sendAlarmMessage(MetricEmergencyEvent currentEmergencyEvent) {
        //发送mq消息异步处理
        MQExchangeEnum heartBeatMQ = MQExchangeEnum.SEND_MESSAGE;
        QueueMessage queueMessage = new QueueMessage(currentEmergencyEvent);

        rabbitTemplate.send(heartBeatMQ.getExchangeName(), heartBeatMQ.getQueueKey(), queueMessage.toAmqpMessage());
    }

    public void emergencyEventStatusChange(MetricEmergencyEvent metricEmergencyEvent, MetricStatusRequest metricStatusRequest) {
        //组装更新异常数据
        assembleNewAbnormalData(metricEmergencyEvent, metricStatusRequest);
        //记录告警事件异常数据记录日志
        LogEventAbnormalData logEventAbnormalData = createNewLogEventAbnormalData(metricEmergencyEvent, metricStatusRequest);

        //更新告警事件
        updateEmergencyEvent(metricEmergencyEvent, logEventAbnormalData);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateEmergencyEvent(MetricEmergencyEvent metricEmergencyEvent, LogEventAbnormalData logEventAbnormalData) {
        metricEmergencyEventDao.updateEventAbnormalDataByGid(metricEmergencyEvent);
        logEventAbnormalDataDao.saveLogEventAbnormalData(logEventAbnormalData);
    }

    public void assembleNewAbnormalData(MetricEmergencyEvent oldEvent, MetricStatusRequest metricStatusRequest) {
        oldEvent.setLevel(metricStatusRequest.getLevel());
        oldEvent.setDescription(metricStatusRequest.getDesc());
        oldEvent.setAbnormalNum(metricStatusRequest.getAbnormalNum());
        oldEvent.setAbnormalData(new Gson().toJson(metricStatusRequest.getAbnormalData()));
    }


    public MetricEmergencyEvent happenNewEmergencyEvent(MetricItem metricItem, MetricStatusRequest metricStatusRequest) {
        //创建新的告警事件
        MetricEmergencyEvent newEvent = createNewEvent(metricItem, metricStatusRequest);
        //记录告警事件状态变更日志
        LogEventStatus logEventStatus = creatNewLogEventStatus(newEvent);
        //记录告警事件异常数据记录日志
        LogEventAbnormalData logEventAbnormalData = createNewLogEventAbnormalData(newEvent, metricStatusRequest);

        //保存一个告警事件
        saveEmergencyEvent(newEvent, logEventStatus, logEventAbnormalData);

        return newEvent;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveEmergencyEvent(MetricEmergencyEvent metricEmergencyEvent, LogEventStatus logEventStatus, LogEventAbnormalData logEventAbnormalData) {
        metricEmergencyEventDao.saveMetricEmergencyEvent(metricEmergencyEvent);
        logEventStatusDao.saveLogEventStatus(logEventStatus);
        logEventAbnormalDataDao.saveLogEventAbnormalData(logEventAbnormalData);
    }

    private LogEventAbnormalData createNewLogEventAbnormalData(MetricEmergencyEvent metricEmergencyEvent, MetricStatusRequest metricStatusRequest) {
        int currentTimeStamp = Utility.getCurrentSecondTimes();

        LogEventAbnormalData logEventAbnormalData = new LogEventAbnormalData();
        logEventAbnormalData.setGid(Utility.generate32UUID());
        logEventAbnormalData.setCreateTime(currentTimeStamp);
        logEventAbnormalData.setMetricGid(metricEmergencyEvent.getMetricGid());
        logEventAbnormalData.setEventGid(metricEmergencyEvent.getGid());
        logEventAbnormalData.setLevel(metricStatusRequest.getLevel());
        logEventAbnormalData.setDescription(metricStatusRequest.getDesc());
        logEventAbnormalData.setAbnormalNum(metricStatusRequest.getAbnormalNum());
        logEventAbnormalData.setAbnormalData(new Gson().toJson(metricStatusRequest.getAbnormalData()));

        return logEventAbnormalData;
    }

    private LogEventStatus creatNewLogEventStatus(MetricEmergencyEvent metricEmergencyEvent) {
        int currentTimeStamp = Utility.getCurrentSecondTimes();

        LogEventStatus logEventStatus = new LogEventStatus();
        logEventStatus.setGid(Utility.generate32UUID());
        logEventStatus.setCreateTime(currentTimeStamp);
        logEventStatus.setMetricGid(metricEmergencyEvent.getMetricGid());
        logEventStatus.setEventGid(metricEmergencyEvent.getGid());
        logEventStatus.setStatus(Constant.EmergencyEventStatus.UN_SOLVE);
        logEventStatus.setOperator("system");

        return logEventStatus;
    }

    private MetricEmergencyEvent createNewEvent(MetricItem metricItem, MetricStatusRequest metricStatusRequest) {
        int currentTimeStamp = Utility.getCurrentSecondTimes();

        MetricEmergencyEvent newEvent = new MetricEmergencyEvent();
        newEvent.setGid(Utility.generate32UUID());
        newEvent.setGroupGid(metricItem.getGroupGid());
        newEvent.setSubGroupGid(metricItem.getSubGroupGid());
        newEvent.setCreateTime(currentTimeStamp);
        newEvent.setUpdateTime(currentTimeStamp);
        newEvent.setMetricGid(metricItem.getGid());
        newEvent.setStatus(Constant.EmergencyEventStatus.UN_SOLVE);
        newEvent.setLevel(metricStatusRequest.getLevel());
        newEvent.setDescription(metricStatusRequest.getDesc());
        newEvent.setAbnormalNum(metricStatusRequest.getAbnormalNum());
        newEvent.setAbnormalData(new Gson().toJson(metricStatusRequest.getAbnormalData()));

        return newEvent;
    }

    @Override
    public MetricEmergencyEvent selectUnSolvedEventByMetric(String metricGid) {
        return metricEmergencyEventDao.selectUnSolvedEventByMetric(metricGid);
    }

}
