package com.va.kotlintaste.net.retrofit.listener;

import okhttp3.HttpUrl;

/**
 * @author Junmeng.Chen
 * @date 2017/11/1
 */

public interface OnUrlChangeListener {

    /**
     * 调用时间是在接口请求服务器之前
     * @param oldUrl
     * @param domainName
     */
    void onUrlChangeBefore(HttpUrl oldUrl,String domainName);

    /**
     * 当 Url 的 BaseUrl 被改变时回调
     * 调用时间是在接口请求服务器之前
     *
     * @param newUrl
     * @param oldUrl
     */
    void onUrlChanged(HttpUrl newUrl,HttpUrl oldUrl);

}
