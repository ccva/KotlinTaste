package com.va.kotlintaste

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import com.va.kotlintaste.constant.DBConstant
import com.va.kotlintaste.service.BackPushService
import com.va.kotlintaste.util.Utils
import kotlinx.android.synthetic.main.activity_cp_test.*
import java.text.SimpleDateFormat
import java.util.*

class CpTestActivity : AppCompatActivity() {

    var serviceInterface: IPushServiceInterface? = null

    var conn: ServiceConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            serviceInterface = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            serviceInterface = IPushServiceInterface.Stub.asInterface(service)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cp_test)

        bindService(Intent(this, BackPushService::class.java), conn, Service.BIND_AUTO_CREATE)

        btn_register.setOnClickListener { register() }

        btn_unRegister.setOnClickListener { unRegister() }

        btn_insert.setOnClickListener { insertByRemoteService() }

        btn_query.setOnClickListener { query() }

        btn_delete.setOnClickListener { deleteData() }
    }

    private fun unRegister() {
        serviceInterface?.unRegisterPushService()
    }

    private fun register() {
        serviceInterface?.registerPushService()
    }

    private fun query() {
        var uri = Uri.parse("content://" + DBConstant.AUTHORITY_PERSON + "/" + DBConstant.PATH_PERSON)
        var arrayOf = arrayOf(DBConstant.KEY_ID, DBConstant.KEY_NAME)
        var cursor = contentResolver.query(uri, arrayOf, null, null, null)

        var sb = StringBuilder()
        var format = SimpleDateFormat("HH:mm")
        sb.append("current time is " + format.format(Calendar.getInstance().time)).append("\n")
        sb.append("current process is " + Utils.getProcessName(this, android.os.Process.myPid())).append("\n")
        while (cursor.moveToNext()) {
            var name = cursor.getString(cursor.getColumnIndex(DBConstant.KEY_NAME))
            sb.append(name).append("\n")
        }

        tv.text = sb.toString()
    }

    private fun deleteData() {
        serviceInterface?.clear()
    }

    private fun insertByRemoteService() {
        serviceInterface?.insert()
    }
}
