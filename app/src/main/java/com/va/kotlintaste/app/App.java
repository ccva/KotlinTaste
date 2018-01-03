package com.va.kotlintaste.app;

import android.app.ActivityManager;
import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.support.annotation.Nullable;

import com.squareup.leakcanary.LeakCanary;
import com.va.kotlintaste.config.GlobalConfig;
import com.va.kotlintaste.di.AppComponent;
import com.va.kotlintaste.di.AppModule;
import com.va.kotlintaste.di.DaggerAppComponent;

import org.jetbrains.annotations.Contract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Junmeng.Chen on 2017/9/14.
 */

public class App extends Application {

    @Inject
    GlobalConfig globalConfig;

    AppComponent appComponent;

    private static App instance;

    @Nullable
    @Contract(pure = true)
    public static App getInstance() {
        if (instance == null) {
            return null;
        }
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        String processName = getProcessName(this, android.os.Process.myPid());
        if (processName != null) {
            boolean defaultProcess = processName.equals(getPackageName());
            if (defaultProcess) {
                initMainProcess();
            } else if (processName.contains(":mqtt")) {
                //TODO-处理mqtt进程的初始化
            }
        }
    }

    private void initMainProcess() {

    }

    public static String getProcessName(Context context, int pid) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null && !runningApps.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }

}
