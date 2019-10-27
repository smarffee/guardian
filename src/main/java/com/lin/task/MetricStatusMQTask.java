package com.lin.task;

import com.google.gson.Gson;
import com.lin.model.QueueMessage;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.MetricStatusServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Lin on 2019/10/27.
 */
@Component
public class MetricStatusMQTask {

    private static final Logger logger = LoggerFactory.getLogger(MetricStatusMQTask.class);

    @Autowired
    private MetricStatusServiceService metricStatusService;

    /**
     * 处理监控状态请求
     *
     * @param message
     */
    @RabbitListener(queues = "guardian.metric_status")
    public void handleStatusUpdate(Message message) {

        logger.info("====> handleStatusUpdate: recieve mq message. start to process.");

        QueueMessage queueMessage = QueueMessage.fromAmqpMessage(message);
        MetricStatusRequest metricStatusRequest = (new Gson()).fromJson(queueMessage.getPayload(), MetricStatusRequest.class);

        logger.info("handleStatusUpdate: mq message is {}", metricStatusRequest);

        metricStatusService.handleMetricStatus(metricStatusRequest);

        logger.info("<==== handleStatusUpdate: successful process mq message.");
    }

}