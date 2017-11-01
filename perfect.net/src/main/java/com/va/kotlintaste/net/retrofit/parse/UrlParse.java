package com.va.kotlintaste.net.retrofit.parse;

import okhttp3.HttpUrl;

/**
 * @author Junmeng.Chen
 * @date 2017/11/1
 */

public interface UrlParse {

    /**
     * 用来动态替换 baseUrl 的解析器
     *
     * @param domainUrl
     * @param originalUrl
     * @return
     */
    HttpUrl parseUrl(HttpUrl domainUrl, HttpUrl originalUrl);

}
