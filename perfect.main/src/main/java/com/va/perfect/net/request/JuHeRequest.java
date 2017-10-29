package com.va.perfect.net.request;

import com.va.perfect.net.response.ResponseCallback;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.net.util.RequestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjm on 17-10-29.
 */

public final class JuHeRequest {

    public static void getCategory(ResponseCallback responseCallback) {

        Map<String, Object> params = new HashMap<>();
        RequestUtils.addSignKey(params);

        RetrofitService.juHeApi.getCategory(params).enqueue(responseCallback);

    }


}
