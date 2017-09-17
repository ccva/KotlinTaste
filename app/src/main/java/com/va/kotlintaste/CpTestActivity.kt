package com.va.kotlintaste

import android.annotation.SuppressLint
import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.text.method.LinkMovementMethod
import com.va.kotlintaste.constant.DBConstant
import com.va.kotlintaste.service.BackPushService
import kotlinx.android.synthetic.main.activity_cp_test.*
import java.text.SimpleDateFormat
import java.util.*

class CpTestActivity : AppCompatActivity() {

    var serviceInterface: IPushServiceInterface? = null

    private var conn: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            serviceInterface = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            serviceInterface = IPushServiceInterface.Stub.asInterface(service)
        }
    }

    private var observer: ContentObserver = object : ContentObserver(Handler()) {

        override fun onChange(selfChange: Boolean) {
            tv.text = StringBuilder(tv.text).append("receive a notify").append("\n").toString()
            query()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cp_test)

        tv.movementMethod = LinkMovementMethod.getInstance()

        contentResolver.registerContentObserver(Uri.parse(DBConstant.PERSON_URI_STRING),
                true, observer)

        bindService(Intent(this, BackPushService::class.java), conn, Service.BIND_AUTO_CREATE)

        btn_register.setOnClickListener { register() }

        btn_unRegister.setOnClickListener { unRegister() }

        btn_insert.setOnClickListener { insertByRemoteService() }

        btn_query.setOnClickListener { query() }

        btn_delete.setOnClickListener { deleteData() }

        btn_update.setOnClickListener { update() }
    }

    private fun update() {
        serviceInterface?.update()
    }

    private fun unRegister() {
        serviceInterface?.unRegisterPushService()
    }

    private fun register() {
        serviceInterface?.registerPushService()
    }

    @SuppressLint("SimpleDateFormat")
    private fun query() {
        val uri = Uri.parse(DBConstant.PERSON_URI_STRING)
        val arrayOf = arrayOf(DBConstant.KEY_ID, DBConstant.KEY_NAME, DBConstant.KEY_WHERE)
        val cursor = contentResolver.query(uri, arrayOf, null, null, null)

        val sb = StringBuilder()
        val format = SimpleDateFormat("HH:mm")
        sb.append("time : " + format.format(Calendar.getInstance().time)).append("\n")
        while (cursor.moveToNext()) {
            val name = cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME))
            val where = cursor.getString(cursor.getColumnIndex(DBConstant.KEY_WHERE))
            sb.append(name).append("\n").append(where).append("\n")
        }

        cursor.close()
        tv.text = StringBuilder(tv.text).append(sb).toString()
    }

    private fun deleteData() {
        serviceInterface?.clear()
    }

    private fun insertByRemoteService() {
        serviceInterface?.insert()
    }
}
