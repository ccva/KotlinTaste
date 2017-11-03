package com.va.perfect.net.api;


import com.va.kotlintaste.net.retrofit.constant.NameConstant;
import com.va.perfect.net.constant.ApiConstant;
import com.va.perfect.net.dao.joke.JokeBean;
import com.va.perfect.net.dao.result.BaseHttpResult;
import com.va.perfect.net.dao.tv.CategoryBean;
import com.va.perfect.net.dao.tv.ChannelBean;
import com.va.perfect.net.dao.tv.ProgramBean;
import com.va.perfect.net.dao.wx.WxChoiceListBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
    @Headers({NameConstant.DOMAIN_NAME_HEADER + ApiConstant.BASE_URL_JU_HE_TV_HEAD_KEY})
    @POST("tv/getCategory")
    Observable<BaseHttpResult<List<CategoryBean>>> getCategory(@Query("key") String key);

    /**
     * 获取 电视频道
     *
     * @param params
     * @return
     */
    @Headers({NameConstant.DOMAIN_NAME_HEADER + ApiConstant.BASE_URL_JU_HE_TV_HEAD_KEY})
    @GET("tv/getChannel")
    Observable<BaseHttpResult<List<ChannelBean>>> getChannel(@QueryMap Map<String, Object> params);

    /**
     * 获取电视界面信息
     *
     * @param params
     * @return
     */
    @Headers({NameConstant.DOMAIN_NAME_HEADER + ApiConstant.BASE_URL_JU_HE_TV_HEAD_KEY})
    @GET("tv/getProgram")
    Observable<BaseHttpResult<List<ProgramBean>>> getProgram(@QueryMap Map<String, Object> params);

    /**
     * 获取笑话接口
     * @param key
     * @return
     */
    @Headers({NameConstant.DOMAIN_NAME_HEADER + ApiConstant.BASE_URL_JU_HE_JOKE_HEAD_KEY})
    @GET("joke/randJoke.php")
    Observable<BaseHttpResult<List<JokeBean>>> getJokeList(@Query("key") String key);

    /**
     * 获取微信精选接口
     * @param params
     * @return
     */
    @Headers({NameConstant.DOMAIN_NAME_HEADER + ApiConstant.BASE_URL_JU_HE_WX_HEAD_KEY})
    @GET("weixin/query")
    Observable<BaseHttpResult<WxChoiceListBean>> getWxChoice(@QueryMap Map<String,Object> params);

}
