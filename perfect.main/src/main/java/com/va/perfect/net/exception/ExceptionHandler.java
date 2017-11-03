package com.va.perfect.net.exception;

import retrofit2.HttpException;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 */

public final class ExceptionHandler {

    public static Throwable handleException(Throwable throwable) {

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            ResponseThrowable ex = new ResponseThrowable("网络错误", httpException, ErrorCode.HTTP_ERROR);
            return ex;
        }

        return throwable;
    }

}
