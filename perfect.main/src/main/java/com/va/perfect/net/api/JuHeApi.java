package com.va.perfect.net.api;

import com.va.perfect.net.dao.result.CategoryDao;


import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cjm on 17-10-29.
 */

public interface JuHeApi {

    /**
     * 获取 电视节目单
     * @return
     */
    @GET("tv/getCategory")
    Call<CategoryDao> getCategory();

}
