package com.va.kotlintaste.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat

/**
 * @author Junmeng.Chen
 * @date 2018/1/2
 */

class NotificationUtils(context: Context) : ContextWrapper(context) {

    private var manager: NotificationManager? = null

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun createNotificationChannel() {
        val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.GREEN); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
        getManager().createNotificationChannel(channel)
    }

    private fun getManager(): NotificationManager {
        if (manager == null) {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return manager as NotificationManager
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun getChannelNotification(title: String, content: String): Notification.Builder {
        return Notification.Builder(applicationContext, id)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true)
                /*VISIBILITY_PUBLIC只有在没有锁屏时会显示通知
                  VISIBILITY_PRIVATE任何情况都会显示通知
                  VISIBILITY_SECRET在安全锁和没有锁屏的情况下显示通知*/
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setNumber(3) //久按桌面图标时允许的此条通知的数量
    }

    fun getNotification_25(title: String, content: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setAutoCancel(true)
    }

    fun sendNotification(title: String, content: String, pendingIntent: PendingIntent?) {
        if (Build.VERSION.SDK_INT >= 26) {
            createNotificationChannel()
            var channelNotification = getChannelNotification(title, content)
            channelNotification.setContentIntent(pendingIntent)
            val notification = channelNotification.build()
            getManager().notify(1, notification)
        } else {
            var notification_25 = getNotification_25(title, content)
            notification_25.setContentIntent(pendingIntent)
            val notification = notification_25.build()
            getManager().notify(1, notification)
        }
    }

    companion object {
        val id = "channel_1"
        val name = "channel_name_1"
    }

}
