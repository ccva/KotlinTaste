package com.va.perfect.net.retrofit;

import retrofit2.Retrofit;

/**
 * Created by cjm on 17-10-29.
 */

public class RetorfitService {

    public static final String BASE_URL_JUHE = "http://japi.juhe.cn/";

    Retrofit juheRetrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL_JUHE)
            .build();


}
