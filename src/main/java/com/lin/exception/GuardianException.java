package com.lin.exception;

import com.lin.model.MessageEnum;

/**
 * Created by Lin on 2019/10/27.
 */
public class GuardianException extends RuntimeException {

    private MessageEnum MessageEnum;

    public GuardianException(MessageEnum MessageEnum) {
        this.MessageEnum = MessageEnum;
    }

    public GuardianException() {
        super();
    }

    public MessageEnum getMessageEnum() {
        return MessageEnum;
    }

    public void setMessageEnum(MessageEnum messageEnum) {
        MessageEnum = messageEnum;
    }
}
