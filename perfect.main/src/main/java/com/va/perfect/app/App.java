package com.va.perfect.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.va.perfect.net.api.ApiUtils;

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
        ApiUtils.init();
    }

    public static Application getContext() {
        return context;
    }
}
