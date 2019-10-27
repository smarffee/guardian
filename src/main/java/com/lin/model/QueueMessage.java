package com.lin.model;

import com.google.gson.Gson;
import com.lin.exception.GuardianException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

/**
 * Created by Lin on 2019/10/27.
 */
public class QueueMessage {

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
        this.payload = new Gson().toJson(payload);
    }

    public static QueueMessage fromAmqpMessage(Message message) {
        Gson gson = new Gson();
        QueueMessage msg;
        try {
            msg = gson.fromJson(new String(message.getBody(), "utf-8"), QueueMessage.class);
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
}
