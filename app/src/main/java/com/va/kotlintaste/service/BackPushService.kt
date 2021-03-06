package com.va.kotlintaste.service

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.va.kotlintaste.IPushServiceInterface
import com.va.kotlintaste.act.MainActivity
import com.va.kotlintaste.R
import com.va.kotlintaste.constant.DbConstant
import com.va.kotlintaste.util.ProcessUtils

class BackPushService : Service() {

    private val TAG = "backpushService"

    override fun onCreate() {
        super.onCreate()

        var foregroundApp = ProcessUtils.getForegroundApp(this)
        if (foregroundApp == "com.va.kotlintaste") {
            Log.i(TAG, "true")
        } else {
            Log.i(TAG, "false")
        }
    }

    var canReceive = false

    private var mIBinder: IPushServiceInterface.Stub = object : IPushServiceInterface.Stub() {
        override fun notification() {
            notifyI()
        }

        override fun update() {
            updateData()
        }

        override fun clear() {
            clearTableData()
        }

        override fun insert() {
            insertData()
        }

        override fun registerPushService() {
            canReceive = true
        }

        override fun unRegisterPushService() {
            canReceive = false
        }
    }

    private fun notifyI() {
        if (!canReceive) {
            return
        }

        buildNotification()
    }

    private fun buildNotification() {

        var builder = Notification.Builder(this)
        builder.setTicker("tickertickertickertickertickertickertickertickertickertickerticker")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setDefaults(Notification.DEFAULT_ALL)


        builder.setContentTitle("title")
        builder.setContentText("content")

        builder.setWhen(System.currentTimeMillis())

        var intent = Intent()
        intent.setClassName("com.va.kotlintaste", MainActivity::class.java.name)
        var bundle = Bundle()
        bundle.putString("cjm", "cjm")
        intent.putExtras(bundle)

        var pendingIntent = PendingIntent.getActivity(this, 100, intent, 0)
        builder.setContentIntent(pendingIntent)

        var notification = builder.build()

        var notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(200, notification)

    }

    private fun updateData() {
        if (!canReceive) {
            return
        }
        val uri = Uri.parse(DbConstant.PERSON_URI_STRING)
        val values = ContentValues()
        values.put(DbConstant.KEY_NAME, "ffcjm")
        val where = DbConstant.KEY_NAME + "= ?"
        val selectionArgs = "cjm"
        contentResolver.update(uri, values, where, arrayOf(selectionArgs))

    }

    private fun insertData() {
        if (!canReceive) {
            return
        }

        val uri = Uri.parse(DbConstant.PERSON_URI_STRING)
        val contentValues = ContentValues()
        contentValues.put(DbConstant.KEY_ID, 1)
        contentValues.put(DbConstant.KEY_NAME, "cjm")
        contentResolver.insert(uri, contentValues)
    }

    private fun clearTableData() {
        if (!canReceive) {
            return
        }
        val uri = Uri.parse(DbConstant.PERSON_URI_STRING)
        contentResolver.delete(uri, null, null)
    }

    override fun onBind(intent: Intent): IBinder? {

        return mIBinder
    }

}
