package com.lin.task;

import com.google.gson.Gson;
import com.lin.model.QueueMessage;
import com.lin.model.db.MetricEmergencyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AlarmMessageMQTask {

    private static final Logger logger = LoggerFactory.getLogger(AlarmMessageMQTask.class);

    @RabbitListener(queues = "guardian.send_message")
    public void sendAlarmMessag(Message message) {

        QueueMessage queueMessage = QueueMessage.fromAmqpMessage(message);
        MetricEmergencyEvent currentEmergencyEvent = (new Gson()).fromJson(queueMessage.getPayload(), MetricEmergencyEvent.class);

        logger.info("sendAlarmMessag: currentEmergencyEvent is {}", currentEmergencyEvent);

    }

}
