package com.lin.service.impl;

import com.lin.dao.HeartBeatDao;
import com.lin.model.MQExchangeEnum;
import com.lin.model.QueueMessage;
import com.lin.model.db.HeartBeat;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.HeartBeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Lin on 2019/10/27.
 */
@Service
public class HeartBeatServiceImpl implements HeartBeatService {

    private static final Logger logger = LoggerFactory.getLogger(HeartBeatServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private HeartBeatDao heartBeatDao;

    @Override
    public void doHeartBeat(MetricStatusRequest metricStatusRequest) {
        //监控指标项心跳一次
        int num = heartBeatDao.heartBeatByKey(metricStatusRequest.getMetricKey());
        if (num != 1) {
            logger.error("doHeartBeat: do heart beat fail. " +
                    "the metricKey is [{}], num is [{}]", metricStatusRequest.getMetricKey(), num);
            return;
        }

        //发送mq消息异步处理
        MQExchangeEnum heartBeatMQ = MQExchangeEnum.METRIC_STATUS;
        QueueMessage queueMessage = new QueueMessage(metricStatusRequest);

        logger.info("doHeartBeat: send rabbitmq message. the msg is {}", queueMessage);

        rabbitTemplate.send(heartBeatMQ.getExchangeName(), heartBeatMQ.getQueueKey(), queueMessage.toAmqpMessage());
    }

    @Override
    public int createHeartBeat(HeartBeat heartBeat) {
        return heartBeatDao.saveHeartBeat(heartBeat);
    }

    @Override
    public List<HeartBeat> selectUnHeartBeatByUpdateTime(int cutOffTimes) {
        return heartBeatDao.selectHeartBeatByUpdateTime(cutOffTimes);
    }

}
