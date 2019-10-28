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

import java.util.List;

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
            //如果保存失败，则返回
            if (currentEmergencyEvent == null) {
                return;
            }
        } else {
            /**
             * 如果预警项正在告警, 说明告警状态发生变更
             * 如果更新没有成功, 则返回
             */
            if (!emergencyEventStatusChange(currentEmergencyEvent, metricStatusRequest)) {
                return;
            }
        }

        //发送告警信息
        sendAlarmMessage(currentEmergencyEvent);
    }

    /**
     * 发送通知消息
     *
     * @param currentEmergencyEvent 当前告警事件
     */
    public void sendAlarmMessage(MetricEmergencyEvent currentEmergencyEvent) {
        //发送mq消息异步处理
        MQExchangeEnum heartBeatMQ = MQExchangeEnum.SEND_MESSAGE;
        QueueMessage queueMessage = new QueueMessage(currentEmergencyEvent);

        rabbitTemplate.send(heartBeatMQ.getExchangeName(), heartBeatMQ.getQueueKey(), queueMessage.toAmqpMessage());
    }

    public boolean emergencyEventStatusChange(MetricEmergencyEvent metricEmergencyEvent, MetricStatusRequest metricStatusRequest) {
        //组装更新数据
        assembleNewAbnormalData(metricEmergencyEvent, metricStatusRequest);
        //记录告警事件异常数据记录日志
        LogEventAbnormalData logEventAbnormalData = createNewLogEventAbnormalData(metricEmergencyEvent, metricStatusRequest);

        //不更新的字段
        metricEmergencyEvent.setId(null);
        metricEmergencyEvent.setGroupGid(null);
        metricEmergencyEvent.setSubGroupGid(null);
        metricEmergencyEvent.setMetricGid(null);
        //更新告警事件
        metricEmergencyEvent.setUpdateTime(Utility.getCurrentSecondTimes());

        return updateEmergencyEvent(metricEmergencyEvent, logEventAbnormalData);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateEmergencyEvent(MetricEmergencyEvent metricEmergencyEvent, LogEventAbnormalData logEventAbnormalData) {
        int num = metricEmergencyEventDao.updateAbnormalDataByGidAndStatus(metricEmergencyEvent);
        if (num == 1) {
            logEventAbnormalDataDao.saveLogEventAbnormalData(logEventAbnormalData);
            return true;
        }

        return false;
    }

    public void assembleNewAbnormalData(MetricEmergencyEvent metricEmergencyEvent, MetricStatusRequest metricStatusRequest) {
        metricEmergencyEvent.setLevel(metricStatusRequest.getLevel());
        metricEmergencyEvent.setDescription(metricStatusRequest.getDesc());
        metricEmergencyEvent.setAbnormalNum(metricStatusRequest.getAbnormalNum());
        metricEmergencyEvent.setAbnormalData(new Gson().toJson(metricStatusRequest.getAbnormalData()));
    }


    public MetricEmergencyEvent happenNewEmergencyEvent(MetricItem metricItem, MetricStatusRequest metricStatusRequest) {
        //创建新的告警事件
        MetricEmergencyEvent newEvent = createNewEvent(metricItem, metricStatusRequest);
        //记录告警事件状态变更日志
        LogEventStatus logEventStatus = creatNewLogEventStatus(newEvent);
        //记录告警事件异常数据记录日志
        LogEventAbnormalData logEventAbnormalData = createNewLogEventAbnormalData(newEvent, metricStatusRequest);

        //保存一个告警事件
        if (saveEmergencyEvent(newEvent, logEventStatus, logEventAbnormalData)) {
            return newEvent;
        }

        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean saveEmergencyEvent(MetricEmergencyEvent metricEmergencyEvent, LogEventStatus logEventStatus, LogEventAbnormalData logEventAbnormalData) {
        int num = metricEmergencyEventDao.saveMetricEmergencyEvent(metricEmergencyEvent);

        if (num == 1) {
            logEventStatusDao.saveLogEventStatus(logEventStatus);
            logEventAbnormalDataDao.saveLogEventAbnormalData(logEventAbnormalData);
            return true;
        }

        return false;
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

    @Override
    public List<MetricEmergencyEvent> selectSolvedEvent() {
        return metricEmergencyEventDao.selectSolvedEvent();
    }

    @Override
    public int deleteByGid(String gid) {
        return metricEmergencyEventDao.deleteByGid(gid);
    }

}
