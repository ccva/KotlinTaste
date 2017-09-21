package com.va.kotlintaste.util;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * Created by Junmeng.Chen on 2017/9/20.
 */

public class ProcessUtils {

    public static String getForegroundApp(Context context) {
        ActivityManager am =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> lr = am.getRunningAppProcesses();
        if (lr == null) {
            return null;
        }

        for (ActivityManager.RunningAppProcessInfo ra : lr) {
            if (ra.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE
                    || ra.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return ra.processName;
            }
        }

        return null;
    }

}
