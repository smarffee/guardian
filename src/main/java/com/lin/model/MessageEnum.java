package com.lin.model;

/**
 * Created by Lin on 2019/10/27.
 */
public enum MessageEnum {

    SUCCESS(200, "response.success"),
    SYSTEM_ERROR(500, "system.error"),
    UNKNOW_ERROR(1000, "response.unknow.error"),
    METRIC_KEY_IS_NULL(100001, "metric.key.is.null"),
    METRIC_LEVEL_ILLEGAL(100002, "metric.level.illegal"),

    ;

    private int code;

    private String key;

    MessageEnum(int code, String key) {
        this.code = code;
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
