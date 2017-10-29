package com.va.perfect.net.response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cjm on 17-10-29.
 */

public abstract class ResponseCallback<T> implements Callback<T> {


    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T t = response.body();
        onResponse(t);
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onResponse(null);
        onFailure(t);
    }

    public abstract void onResponse(T result);

    public abstract void onFailure(Throwable throwable);

}
