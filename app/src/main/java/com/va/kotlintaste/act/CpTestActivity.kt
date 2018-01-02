package com.va.kotlintaste.act

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
import android.widget.TextView
import com.va.kotlintaste.IPushServiceInterface
import com.va.kotlintaste.R
import com.va.kotlintaste.constant.DbConstant
import com.va.kotlintaste.service.BackPushService
import com.va.kotlintaste.toast
import com.va.kotlintaste.util.ProcessUtils
import kotlinx.android.synthetic.main.activity_cp_test.*
import java.text.SimpleDateFormat
import java.util.*

class CpTestActivity : AppCompatActivity() {

    private val TAG = CpTestActivity::class.java.simpleName

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

        var foregroundApp = ProcessUtils.getForegroundApp(this)
        if (foregroundApp == "com.va.kotlintaste") {
            toast("foreground")
        } else {
            toast("not foreground")
        }

        setContentView(R.layout.activity_cp_test)

        tv.movementMethod = LinkMovementMethod.getInstance()

        contentResolver.registerContentObserver(Uri.parse(DbConstant.PERSON_URI_STRING),
                true, observer)

        bindService(Intent(this, BackPushService::class.java), conn, Service.BIND_AUTO_CREATE)

        btn_register.setOnClickListener { register() }

        btn_unRegister.setOnClickListener { unRegister() }

        btn_insert.setOnClickListener { insertByRemoteService() }

        btn_query.setOnClickListener { query() }

        btn_delete.setOnClickListener { deleteData() }

        btn_update.setOnClickListener { update() }

        btn_notify.setOnClickListener { notifyI() }

        btn_clear_text.setOnClickListener { clearTextContent(tv) }
    }

    private fun clearTextContent(tv: TextView?) {
        tv?.text = ""
    }

    private fun notifyI() {
        serviceInterface?.notification()
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

        var startSb = StringBuilder()
        startSb.append("begin query")
                .append("\n")

        changeToUiThreadShow(tv, startSb)

        Thread(Runnable {
            var queryingSb = StringBuilder()
            queryingSb.append("querying ...")
                    .append("\n")
            changeToUiThreadShow(tv, queryingSb)

            kotlin.run {
                val uri = Uri.parse(DbConstant.PERSON_URI_STRING)
                val arrayOf = arrayOf(DbConstant.KEY_ID, DbConstant.KEY_NAME, DbConstant.KEY_WHERE)
                val cursor = contentResolver.query(uri, arrayOf, null, null, null)
                val format = SimpleDateFormat("HH:mm")
                val sb = StringBuilder()
                sb.append(format.format(Date())).append("\n")

                if (cursor.isAfterLast) {
                    sb.append("there is nothing!")
                            .append("\n")
                } else {
                    while (cursor.moveToNext()) {
                        val name = cursor.getString(cursor.getColumnIndex(DbConstant.KEY_NAME))
                        val where = cursor.getString(cursor.getColumnIndex(DbConstant.KEY_WHERE))
                        sb.append(name)
                                .append("\n")
                                .append(where)
                                .append("\n")
                    }
                }

                cursor.close()

                changeToUiThreadShow(tv, sb)
            }
        }).start()

    }

    private fun changeToUiThreadShow(textView: TextView, sb: StringBuilder) {
        runOnUiThread { textView.text = StringBuilder(textView.text).append(sb).toString() }
    }

    private fun deleteData() {
        serviceInterface?.clear()
    }

    private fun insertByRemoteService() {
        serviceInterface?.insert()
    }
}
