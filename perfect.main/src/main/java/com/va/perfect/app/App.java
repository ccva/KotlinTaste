package com.va.perfect.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.va.kotlintaste.net.retrofit.RetrofitManager;
import com.va.perfect.net.constant.ApiConstant;

/**
 * @author Junmeng.Chen
 * @date 2017/10/31
 */

public class App extends Application {

    private static Application context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Utils.init(this);
        RetrofitManager.getInstance().putDomain(ApiConstant.BASE_URL_JU_HE_TV_HEAD_KEY, ApiConstant.BASE_URL_JU_HE_TV);
        RetrofitManager.getInstance().putDomain(ApiConstant.BASE_URL_JU_HE_JOKE_HEAD_KEY, ApiConstant.BASE_URL_JU_HE_JOKE);
        RetrofitManager.getInstance().putDomain(ApiConstant.BASE_URL_JU_HE_WX_HEAD_KEY, ApiConstant.BASE_URL_JU_HE_WX);
        RetrofitManager.getInstance().putDomain(ApiConstant.BASE_URL_JU_HE_POST_CODE_KEY, ApiConstant.BASE_URL_JU_HE_POST_CODE);
    }

    public static Application getContext() {
        return context;
    }
}
