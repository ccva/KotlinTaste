package com.va.kotlintaste.service;

import android.annotation.SuppressLint;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

/**
 * Created by Junmeng.Chen on 2017/9/22.
 */

@SuppressLint("OverrideAbstract")
public class ListenerService extends NotificationListenerService {


    public static final String TAG = ListenerService.class.getSimpleName();
    
    
    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        Log.i(TAG, "onListenerConnected: ");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Log.i(TAG, "onNotificationPosted: ");
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.i(TAG, "onNotificationRemoved: ");
    }
}
