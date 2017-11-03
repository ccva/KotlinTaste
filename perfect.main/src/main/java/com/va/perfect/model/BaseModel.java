package com.va.perfect.model;

import com.va.perfect.net.dao.result.BaseHttpResult;
import com.va.perfect.net.exception.ExceptionHandler;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 */

public class BaseModel {

    public static class HandleResult<T> implements Function<BaseHttpResult<T>, T> {

        @Override
        public T apply(BaseHttpResult<T> baseHttpResult) {
            if (baseHttpResult.getError_code() != 0) {
                throw new RuntimeException(baseHttpResult.getError_code() + ""
                        + baseHttpResult.getResult() == null ? baseHttpResult.getReason() : "");
            }
            return baseHttpResult.getResult();
        }
    }

    public static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable throwable) throws Exception {
            return Observable.error(ExceptionHandler.handleException(throwable));
        }
    }

}
