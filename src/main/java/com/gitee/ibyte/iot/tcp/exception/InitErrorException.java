package com.gitee.ibyte.iot.tcp.exception;

public class InitErrorException extends RuntimeException {

    private static final long serialVersionUID = 4401440531171871948L;

    private int errorCode = 1;

    private String errorMsg;

    protected InitErrorException() {

    }

    public InitErrorException(String errorMsg, Throwable e) {
        super(errorMsg, e);
        this.errorMsg = errorMsg;
    }

    public InitErrorException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }
}
