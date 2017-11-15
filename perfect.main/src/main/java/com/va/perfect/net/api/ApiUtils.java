package com.va.perfect.net.api;

import com.va.kotlintaste.net.retrofit.RetrofitManager;
import com.va.perfect.net.constant.ApiConstant;

/**
 * @author Junmeng.Chen
 * @date 2017/11/13
 */

public class ApiUtils {

    public static void init(){
        RetrofitManager.getInstance().putDomain(ApiConstant.BASE_URL_JU_HE_TV_HEAD_KEY, ApiConstant.BASE_URL_JU_HE_TV);
        RetrofitManager.getInstance().putDomain(ApiConstant.BASE_URL_JU_HE_JOKE_HEAD_KEY, ApiConstant.BASE_URL_JU_HE_JOKE);
        RetrofitManager.getInstance().putDomain(ApiConstant.BASE_URL_JU_HE_WX_HEAD_KEY, ApiConstant.BASE_URL_JU_HE_WX);
        RetrofitManager.getInstance().putDomain(ApiConstant.BASE_URL_JU_HE_POST_CODE_KEY, ApiConstant.BASE_URL_JU_HE_POST_CODE);
    }

}
