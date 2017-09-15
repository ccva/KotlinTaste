package com.va.kotlintaste.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import java.util.List;

/**
 * Created by Junmeng.Chen on 2017/9/14.
 */

public class App extends Application {

    private static App instance;

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
