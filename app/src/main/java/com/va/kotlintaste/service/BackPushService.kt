package com.va.kotlintaste.service

import android.app.Service
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import com.va.kotlintaste.IPushServiceInterface
import com.va.kotlintaste.constant.DBConstant

class BackPushService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    var canReceive = false

    private var mIBinder: IPushServiceInterface.Stub = object : IPushServiceInterface.Stub() {
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

    private fun updateData() {
        if (!canReceive){
            return
        }
        val uri = Uri.parse(DBConstant.PERSON_URI_STRING)
        val values = ContentValues()
        values.put(DBConstant.KEY_NAME,"ffcjm")
        val where = DBConstant.KEY_NAME + "= ?"
        val selectionArgs = "cjm"
        contentResolver.update(uri,values,where, arrayOf(selectionArgs))

    }

    private fun insertData() {
        if (!canReceive) {
            return
        }

        val uri = Uri.parse(DBConstant.PERSON_URI_STRING)
        val contentValues = ContentValues()
        contentValues.put(DBConstant.KEY_ID, 1)
        contentValues.put(DBConstant.KEY_NAME, "cjm")
        contentResolver.insert(uri, contentValues)
    }

    private fun clearTableData() {
        if (!canReceive) {
            return
        }
        val uri = Uri.parse(DBConstant.PERSON_URI_STRING)
        contentResolver.delete(uri, null, null)
    }

    override fun onBind(intent: Intent): IBinder? {

        return mIBinder
    }

}
