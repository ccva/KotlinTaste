package com.va.perfect.net.retrofit;

import com.va.perfect.net.api.JuHeApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by cjm on 17-10-29.
 */

public class RetrofitService {

    public static final String BASE_URL_JUHE = "http://japi.juhe.cn/";

    public static Retrofit juHeRetrofit = new Retrofit.Builder()
            .addConverterFactory(FastJsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
            .baseUrl(BASE_URL_JUHE)
            .build();

    public static JuHeApi juHeApi = juHeRetrofit.create(JuHeApi.class);

}
