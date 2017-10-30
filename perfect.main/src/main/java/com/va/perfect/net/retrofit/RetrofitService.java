package com.va.perfect.net.retrofit;

import com.va.perfect.net.api.JuHeApi;
import com.va.perfect.net.util.RequestUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * @author cjm
 * @date 17-10-29
 */

public class RetrofitService {

    public static final String BASE_URL_JUHE = "http://japi.juhe.cn/";

    public static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new AddParamInterceptor())
            .build();

    public static Retrofit juHeRetrofit = new Retrofit.Builder()
            .addConverterFactory(FastJsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
            .baseUrl(BASE_URL_JUHE)
            .client(okHttpClient)
            .build();

    public static JuHeApi juHeApi = juHeRetrofit.create(JuHeApi.class);

    /**
     * 统一添加 相同的请求参数
     */
    public static class AddParamInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", RequestUtils.SIGN_KEY)
                    .build();

            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }

}
