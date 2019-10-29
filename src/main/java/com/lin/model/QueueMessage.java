package com.lin.model;

import com.google.gson.Gson;
import com.lin.exception.GuardianException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by Lin on 2019/10/27.
 */
public class QueueMessage {

    private static final Logger logger = LoggerFactory.getLogger(QueueMessage.class);

    private String msgId;

    private String payload;

    public QueueMessage() {
        msgId = UUID.randomUUID().toString();
    }

    public QueueMessage(String payload) {
        msgId = UUID.randomUUID().toString();
        this.payload = payload;
    }

    public QueueMessage(Object payload) {
        msgId = UUID.randomUUID().toString();
        this.payload = new Gson().toJson(payload);
    }

    public static QueueMessage fromAmqpMessage(Message message) {
        Gson gson = new Gson();
        QueueMessage msg;
        try {
            msg = gson.fromJson(new String(message.getBody(), "utf-8"), QueueMessage.class);
            logger.info("fromAmqpMessage: the msgId is [{}]", msg.getMsgId());
        } catch (UnsupportedEncodingException e) {
            throw new GuardianException(MessageEnum.SYSTEM_ERROR);
        }
        return msg;
    }

    public Message toAmqpMessage() {
        Gson gson = new Gson();
        Message message;
        try {
            message = MessageBuilder.withBody(gson.toJson(this).getBytes("UTF-8")).build();
            logger.info("toAmqpMessage: the msgId is [{}]", this.getMsgId());
        } catch (UnsupportedEncodingException e) {
            throw new GuardianException(MessageEnum.SYSTEM_ERROR);
        }
        return message;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "QueueMessage{" +
                "msgId='" + msgId + '\'' +
                ", payload='" + payload + '\'' +
                '}';
    }
}
