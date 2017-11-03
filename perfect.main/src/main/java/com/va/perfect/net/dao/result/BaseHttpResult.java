package com.va.perfect.net.dao.result;

/**
 * @author Junmeng.Chen
 * @date 2017/10/30
 */

public class BaseHttpResult<T> {

    private int error_code;
    private String reason;
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
