package com.va.perfect.net.api;

import com.va.perfect.net.dao.result.CategoryBean;
import com.va.perfect.net.dao.result.ChannelBean;
import com.va.perfect.net.dao.result.JuHeHttpResult;
import com.va.perfect.net.dao.result.ProgramBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * @author cjm
 * @date 17-10-29
 */

public interface JuHeApi {

    /**
     * 获取 电视节目单
     *
     * @return
     */
    @POST("tv/getCategory")
    Observable<JuHeHttpResult<List<CategoryBean>>> getCategory();

    /**
     * 获取 电视频道
     *
     * @param params
     * @return
     */
    @GET("tv/getChannel")
    Observable<JuHeHttpResult<List<ChannelBean>>> getChannel(@QueryMap Map<String, Object> params);

    /**
     * 获取电视界面信息
     *
     * @param params
     * @return
     */
    @GET("tv/getProgram")
    Observable<JuHeHttpResult<List<ProgramBean>>> getProgram(@QueryMap Map<String, Object> params);

}
