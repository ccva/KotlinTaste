package com.va.perfect.net.api;

import com.va.perfect.net.dao.result.CategoryDao;
import com.va.perfect.net.dao.result.ChannelDao;


import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by cjm on 17-10-29.
 */

public interface JuHeApi {

    /**
     * 获取 电视节目单
     * @return
     */
    @POST("tv/getCategory")
    Call<CategoryDao> getCategory(@QueryMap(encoded = true) Map<String,Object> params);

    /**
     * 获取 电视节目单
     * @return
     */
    @POST("tv/getCategory")
    Observable<CategoryDao> getCategory(@Query("key")String key);

    @GET("tv/getChannel")
    Observable<ChannelDao> getChannel(@QueryMap Map<String,Object> params);

}
