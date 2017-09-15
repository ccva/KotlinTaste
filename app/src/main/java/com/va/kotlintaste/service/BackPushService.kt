package com.va.kotlintaste.service

import android.app.Service
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import com.va.kotlintaste.IPushServiceInterface
import com.va.kotlintaste.constant.DBConstant
import com.va.kotlintaste.util.Utils

class BackPushService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    var canReceive = false

    private var mIBinder: IPushServiceInterface.Stub = object : IPushServiceInterface.Stub() {
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

    private fun insertData() {
        if (!canReceive) {
            return
        }

        var uri = Uri.parse("content://" + DBConstant.AUTHORITY_PERSON + "/" + DBConstant.PATH_PERSON)
        var contentValues = ContentValues()
        contentValues.put(DBConstant.KEY_ID, 1)
        contentValues.put(DBConstant.KEY_NAME, " cjm \n call provider process is :" + Utils.getProcessName(this, android.os.Process.myPid()))
        contentResolver.insert(uri, contentValues)
    }

    private fun clearTableData() {
        if (!canReceive) {
            return
        }
        var uri = Uri.parse("content://" + DBConstant.AUTHORITY_PERSON + "/" + DBConstant.PATH_PERSON)
        contentResolver.delete(uri, null, null)
    }

    override fun onBind(intent: Intent): IBinder? {

        return mIBinder
    }

}
