package com.va.perfect.net.exception;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 */

public class ResponseThrowable extends Throwable {

    private int errorCode;

    public ResponseThrowable(int errorCode) {
        this.errorCode = errorCode;
    }

    public ResponseThrowable(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ResponseThrowable(String message, Throwable cause, int errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ResponseThrowable(Throwable cause, int errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ResponseThrowable(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
