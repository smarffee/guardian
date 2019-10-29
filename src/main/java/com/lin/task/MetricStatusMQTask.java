package com.lin.task;

import com.google.gson.Gson;
import com.lin.model.QueueMessage;
import com.lin.model.db.MetricItem;
import com.lin.model.status.MetricStatusRequest;
import com.lin.service.MetricItemService;
import com.lin.service.MetricStatusServiceService;
import com.lin.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 处理监控项状态变更请求
 * Created by Lin on 2019/10/27.
 */
@Component
public class MetricStatusMQTask {

    private static final Logger logger = LoggerFactory.getLogger(MetricStatusMQTask.class);

    @Autowired
    private MetricStatusServiceService metricStatusService;

    @Autowired
    private MetricItemService metricItemService;

    /**
     * 处理监控状态请求
     *
     * @param message
     */
    @RabbitListener(queues = "guardian.metric_status")
    public void handleStatusUpdate(Message message) {

        QueueMessage queueMessage = QueueMessage.fromAmqpMessage(message);
        MetricStatusRequest metricStatusRequest = (new Gson()).fromJson(queueMessage.getPayload(), MetricStatusRequest.class);

        logger.info("====> handleStatusUpdate: mq message is {}", queueMessage);

        MetricItem metricItem = metricItemService.selectByMetricKey(metricStatusRequest.getMetricKey());
        //如果没有此监控指标或者监控指标没有启用
        if (metricItem == null ||
                metricItem.getStatus() != Constant.MetricStatus.ENABLE) {
            return;
        }

        metricStatusService.handleMetricStatus(metricItem, metricStatusRequest);

        logger.info("<==== handleStatusUpdate: successful process mq message. mq message is {}", metricStatusRequest);
    }

}
