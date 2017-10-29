package com.va.perfect.net.retrofit;

import com.va.perfect.net.api.JuHeApi;

import retrofit2.Retrofit;

/**
 * Created by cjm on 17-10-29.
 */

public class RetorfitService {

    public static final String BASE_URL_JUHE = "http://japi.juhe.cn/";

    public static Retrofit juHeRetrofit = new Retrofit.Builder()
//            .addConverterFactory(FastJsonConverterFactory.create())
            //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
            .baseUrl(BASE_URL_JUHE)
            .build();

    public static JuHeApi juHeApi = juHeRetrofit.create(JuHeApi.class);

}
