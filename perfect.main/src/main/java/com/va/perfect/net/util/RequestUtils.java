package com.va.perfect.net.util;

import java.util.Map;

/**
 * Created by cjm on 17-10-29.
 */

public class RequestUtils {

    public static final String SIGN_KEY = "4f7632a0843fc1e05a84393a4d55e5f2";

    public static Map addSignKey(Map params){

        if (params == null) {
            throw new NullPointerException("params is empty");
        }

        params.put("key",SIGN_KEY);

        return params;

    }

}
