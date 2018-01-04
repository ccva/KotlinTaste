package com.va.kotlintaste.service

import android.app.PendingIntent
import android.app.Service
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import android.util.Log
import com.va.kotlintaste.IPushServiceInterface
import com.va.kotlintaste.constant.DbConstant
import com.va.kotlintaste.util.NotificationUtils
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
        override fun query() {
            queryData()
        }

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

    private fun queryData() {
        if (!canReceive){
            return
        }
    }

    private fun notifyI() {
        if (!canReceive) {
            return
        }

        buildNotification()
    }

    private fun buildNotification() {

        var notificationUtils = NotificationUtils(this)
        var intent = Intent()
        intent.setClassName("com.va.perfect","com.va.perfect.main.MainActivity")
        var pendingIntent = PendingIntent.getActivity(this, 200, intent, 0)
        notificationUtils.sendNotification("jump to another","start jump",pendingIntent)

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
