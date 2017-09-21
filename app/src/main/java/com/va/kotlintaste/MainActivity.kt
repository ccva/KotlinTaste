package com.va.kotlintaste

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.RemoteViews



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_to_sign.setOnClickListener { startActivity(Intent(this, SignActivity::class.java)) }

        btn_to_multi.setOnClickListener { startActivity(Intent(this, MultiShapeActivity::class.java)) }

        btn_to_radar.setOnClickListener { startActivity(Intent(this, RadarActivity::class.java)) }

        btn_cp_test.setOnClickListener { startActivity(Intent(this, CpTestActivity::class.java)) }

        btn_notify.setOnClickListener { buildNotify() }
    }


    private fun buildNotify() {
        var notificationService = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val view_custom = RemoteViews(packageName, R.layout.view_custom)
//        view_custom.setImageViewResource(R.id.custom_icon, R.drawable.icon)
        view_custom.setTextViewText(R.id.tv_custom_title, "今日头条")
        view_custom.setTextViewText(R.id.tv_custom_content, "金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果。")

        var notification = Notification()

        notification.contentView = view_custom
        var pendingIntent = PendingIntent.getActivity(this, 200, Intent(this, CpTestActivity::class.java), 0)
        notification.contentIntent = pendingIntent



//        var builder = Notification.Builder(this)
//        builder.setAutoCancel(true)
//        builder.setDefaults(Notification.DEFAULT_VIBRATE)
//        builder.setContent(view_custom)
////        builder.setContentTitle("title")
////        builder.setContentText("contentText")
////        builder.setSmallIcon(R.mipmap.ic_launcher_round)
//        builder.setContentIntent(pendingIntent)
//        var notification = builder.build()

        notificationService.notify(100, notification)

    }

    private var time = 0L

    override fun onBackPressed() {

        if (System.currentTimeMillis() - time > 2000) {
            time = System.currentTimeMillis()
            toast("再按一次退出程序")
        } else {
            finish()
        }
    }
}
