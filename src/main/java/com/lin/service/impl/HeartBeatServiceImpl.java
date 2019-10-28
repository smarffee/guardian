package com.lin.service.impl;

import com.lin.dao.HeartBeatDao;
import com.lin.model.MQExchangeEnum;
import com.lin.model.QueueMessage;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.HeartBeatService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Lin on 2019/10/27.
 */
@Service
public class HeartBeatServiceImpl implements HeartBeatService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private HeartBeatDao heartBeatDao;

    @Override
    public void doHeartBeat(MetricStatusRequest metricStatusRequest) {
        //监控指标项心跳一次
        heartBeatDao.heartBeatByKey(metricStatusRequest.getMetricKey());

        //发送mq消息异步处理
        MQExchangeEnum heartBeatMQ = MQExchangeEnum.METRIC_STATUS;
        QueueMessage queueMessage = new QueueMessage(metricStatusRequest);

        rabbitTemplate.send(heartBeatMQ.getExchangeName(), heartBeatMQ.getQueueKey(), queueMessage.toAmqpMessage());
    }

}
