package com.va.perfect.net.retrofit;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.va.kotlintaste.net.retrofit.RetrofitManager;
import com.va.perfect.app.App;
import com.va.perfect.net.api.JuHeApi;
import com.va.perfect.net.constant.ApiConstant;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
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

    public static final String TAG = RetrofitService.class.getSimpleName();

    /**
     * 设置缓存路径
     */
    private static File httpCacheDirectory = new File(App.getContext().getCacheDir(), "responses");
    /**
     * 设置缓存 10M
     */
    private static Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);

    /**
     * OkHttpClient 配置
     */
    public static OkHttpClient okHttpClient = RetrofitManager.getInstance()
            .with(new OkHttpClient.Builder())
//            .addInterceptor(new RequestCacheInterceptor())
//            .addInterceptor(new ResponseCacheInterceptor())
//            .cache(cache)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build();

    /**
     * Retrofit
     */
    public static Retrofit juHeRetrofit = new Retrofit.Builder()
            .addConverterFactory(FastJsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
            .baseUrl(ApiConstant.BASE_URL_JU_HE_TV)
            .client(okHttpClient)
            .build();

    /**
     * Retrofit create Clazz
     */
    public static JuHeApi juHeApi = juHeRetrofit.create(JuHeApi.class);

    /**
     * 拦截器 统一添加 相同的请求参数
     */
    public static class AddParamInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", ApiConstant.TV_SIGN_KEY)
                    .build();

            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }

    /**
     * 拦截器
     */
    public static class RequestCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isConnected()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtils.v(TAG, "net is not connected");
            }

            return chain.proceed(request);
        }
    }

    /**
     * 拦截器
     */
    public static class ResponseCacheInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Response response = chain.proceed(chain.request());

            if (!NetworkUtils.isConnected()) {
                // 无网络时 设置缓存超时时间0个小时
                int maxAge = 0 * 60;
                LogUtils.v(TAG, "no network maxStale=" + maxAge);
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        // 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .removeHeader("Pragma")
                        .build();
            } else {
                //  有网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 7;
                LogUtils.v(TAG, "has network maxAge=" + maxStale);
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
                LogUtils.v(TAG, "response build maxStale=" + maxStale);
            }
            return response;
        }
    }

}
