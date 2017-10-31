package com.va.perfect.app;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

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
    }

    public static Application getContext() {
        return context;
    }
}
