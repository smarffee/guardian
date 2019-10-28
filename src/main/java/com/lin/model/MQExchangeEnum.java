package com.lin.model;

/**
 * Created by Lin on 2019/10/27.
 */
public enum MQExchangeEnum {

    METRIC_STATUS("ex_guardian", "guardian.metric_status", "metric_status"),
    SEND_MESSAGE("ex_guardian", "guardian.send_message", "send_message"),

    ;

    String exchangeName;

    String queueName;

    String queueKey;

    MQExchangeEnum(String exchangeName, String queueName, String queueKey) {
        this.exchangeName = exchangeName;
        this.queueName = queueName;
        this.queueKey = queueKey;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueKey() {
        return queueKey;
    }

    public void setQueueKey(String queueKey) {
        this.queueKey = queueKey;
    }
}
