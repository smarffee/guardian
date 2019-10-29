package com.lin.service.impl;

import com.google.gson.Gson;
import com.lin.model.EmergencyLevelEnum;
import com.lin.model.MQExchangeEnum;
import com.lin.model.QueueMessage;
import com.lin.model.db.LogEventAbnormalData;
import com.lin.model.db.LogEventStatus;
import com.lin.model.db.MetricEmergencyEvent;
import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.MetricEmergencyEventService;
import com.lin.service.MetricStatusServiceService;
import com.lin.util.Constant;
import com.lin.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lin on 2019/10/27.
 */
@Service
public class MetricStatusServiceImpl implements MetricStatusServiceService {

    private static final Logger logger = LoggerFactory.getLogger(MetricStatusServiceImpl.class);

    @Autowired
    private MetricEmergencyEventService metricEmergencyEventService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void handleMetricStatus(MetricItem metricItem, MetricStatusRequest metricStatusRequest) {

        //查询当前预警项的告警事件状态
        MetricEmergencyEvent metricEmergencyEvent =
                metricEmergencyEventService.selectUnSolvedEventByMetric(metricItem.getGid());

        //如果监控指标状态正常
        if (metricEmergencyEvent == null &&
                metricStatusRequest.getLevel() == EmergencyLevelEnum.OK.getLevel()) {
            return;
        }

        //告警事件状态没有改变
        if (!emergencyEventChanged(metricEmergencyEvent, metricStatusRequest)) {
            return;
        }

        logger.info("handleMetricStatus: metric status has changed. metricStatusRequest is {}", metricStatusRequest);

        //告警事件状态变更
        emergencyEventChanged(metricEmergencyEvent, metricItem, metricStatusRequest);
    }

    /**
     * 监控项告警状态发生变更
     *
     * @param currentEmergencyEvent 监控项当前告警事件
     * @param metricItem 监控项
     * @param metricStatusRequest 监控项状态变更请求
     */
    public void emergencyEventChanged(MetricEmergencyEvent currentEmergencyEvent, MetricItem metricItem, MetricStatusRequest metricStatusRequest) {

        if (currentEmergencyEvent == null) {
            logger.info("emergencyEventChanged: happen a new emergency event. metricStatusRequest is {}", metricStatusRequest);

            //如果预警项当前没有告警, 说明产生了一个新的告警
            currentEmergencyEvent = happenNewEmergencyEvent(metricItem, metricStatusRequest);
            //如果保存失败，则返回
            if (currentEmergencyEvent == null) {
                return;
            }
        } else {
            logger.info("emergencyEventChanged: the emergency event status change. metricStatusRequest is {}", metricStatusRequest);
            /**
             * 如果预警项正在告警,
             * 说明告警状态发生变更
             * 更新告警状态
             * 如果更新没有成功, 则返回
             */
            if (!changeEmergencyEventStatus(currentEmergencyEvent, metricStatusRequest)) {
                return;
            }
        }

        //发送通知消息
        sendAlarmMessage(currentEmergencyEvent);
    }

    /**
     * 发生一个新的告警事件
     *
     * @param metricItem
     * @param metricStatusRequest
     * @return
     */
    public MetricEmergencyEvent happenNewEmergencyEvent(MetricItem metricItem, MetricStatusRequest metricStatusRequest) {
        //创建新的告警事件
        MetricEmergencyEvent newEvent = createNewEvent(metricItem, metricStatusRequest);
        //记录告警事件状态变更日志
        LogEventStatus logEventStatus = creatLogEventStatus(newEvent);
        //记录告警事件异常数据记录日志
        LogEventAbnormalData logEventAbnormalData = createLogEventAbnormalData(newEvent, metricStatusRequest);

        //保存一个告警事件
        if (metricEmergencyEventService.saveNewEmergencyEvent(newEvent, logEventStatus, logEventAbnormalData)) {
            logger.info("happenNewEmergencyEvent: save new emergency event success. metricStatusRequest is {}", metricStatusRequest);
            return newEvent;
        }

        logger.error("happenNewEmergencyEvent: save new emergency event fail. metricStatusRequest is {}", metricStatusRequest);

        return null;
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

    /**
     * 改变告警事件状态
     * @param metricEmergencyEvent
     * @param metricStatusRequest
     * @return
     */
    public boolean changeEmergencyEventStatus(MetricEmergencyEvent metricEmergencyEvent, MetricStatusRequest metricStatusRequest) {
        //组装更新数据
        MetricEmergencyEvent updateEvent = assembleUpdateEventData(metricStatusRequest);
        //记录告警事件异常数据记录日志
        LogEventAbnormalData logEventAbnormalData = createLogEventAbnormalData(metricEmergencyEvent, metricStatusRequest);
        LogEventStatus logEventStatus = null;
        if (updateEvent.getLevel() == EmergencyLevelEnum.OK.getLevel()) {
            //记录告警事件状态变更日志
            logEventStatus = creatLogEventStatus(metricEmergencyEvent);
            logEventStatus.setStatus(Constant.EmergencyEventStatus.SOLEVD);
            updateEvent.setStatus(Constant.EmergencyEventStatus.SOLEVD);
        }

        if (metricEmergencyEventService.updateAbnormalData(updateEvent, metricEmergencyEvent, logEventStatus, logEventAbnormalData)) {

            metricEmergencyEvent.setUpdateTime(updateEvent.getUpdateTime());
            metricEmergencyEvent.setLevel(updateEvent.getLevel());
            metricEmergencyEvent.setDescription(updateEvent.getDescription());
            metricEmergencyEvent.setAbnormalNum(updateEvent.getAbnormalNum());
            metricEmergencyEvent.setAbnormalData(updateEvent.getAbnormalData());
            metricEmergencyEvent.setStatus(updateEvent.getStatus());

            return true;
        }

        return false;
    }

    public MetricEmergencyEvent assembleUpdateEventData(MetricStatusRequest metricStatusRequest) {

        MetricEmergencyEvent updateEvent = new MetricEmergencyEvent();

        updateEvent.setUpdateTime(Utility.getCurrentSecondTimes());
        updateEvent.setLevel(metricStatusRequest.getLevel());
        updateEvent.setDescription(metricStatusRequest.getDesc());
        updateEvent.setAbnormalNum(metricStatusRequest.getAbnormalNum());
        updateEvent.setAbnormalData(new Gson().toJson(metricStatusRequest.getAbnormalData()));

        return updateEvent;
    }

    private LogEventAbnormalData createLogEventAbnormalData(MetricEmergencyEvent metricEmergencyEvent, MetricStatusRequest metricStatusRequest) {
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

    private LogEventStatus creatLogEventStatus(MetricEmergencyEvent metricEmergencyEvent) {
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

    /**
     * 发送通知消息
     *
     * @param currentEmergencyEvent 当前告警事件
     */
    public void sendAlarmMessage(MetricEmergencyEvent currentEmergencyEvent) {
        //发送mq消息异步处理
        MQExchangeEnum heartBeatMQ = MQExchangeEnum.SEND_MESSAGE;
        QueueMessage queueMessage = new QueueMessage(currentEmergencyEvent);

        logger.info("sendAlarmMessage: start to send alarm message mq. currentEmergencyEvent is {}", currentEmergencyEvent);

        rabbitTemplate.send(heartBeatMQ.getExchangeName(), heartBeatMQ.getQueueKey(), queueMessage.toAmqpMessage());
    }

    private boolean emergencyEventChanged(MetricEmergencyEvent metricEmergencyEvent, MetricStatusRequest metricStatusRequest) {
        if (metricEmergencyEvent == null) {
            return true;
        }
        //监控等级发生改变
        if (metricEmergencyEvent.getLevel() != metricStatusRequest.getLevel()) {
            return true;
        }
        //异常数据数量发生改变
        if (metricEmergencyEvent.getAbnormalNum() != metricStatusRequest.getAbnormalNum()) {
            return true;
        }

        return false;
    }

}
