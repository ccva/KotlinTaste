package com.va.perfect.net.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cjm on 17-10-29.
 */

public interface JuHeApi {

    /**
     * 获取 电视节目单
     * @param params
     * @return
     */
    @GET("tv/getCategory")
    Call<String> getCategory(Map<Object, Object> params);

}
