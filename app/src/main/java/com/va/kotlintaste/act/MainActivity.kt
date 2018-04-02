package com.va.kotlintaste.act

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import android.widget.RemoteViews
import com.va.kotlintaste.R
import com.va.kotlintaste.config.GlobalConfig
import com.va.kotlintaste.toast
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    private var globalConfig: GlobalConfig? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        if (intent != null && intent.extras != null) {
            var string = intent.extras.getString("cjm")
            if ("cjm" == string) {
                startActivity(Intent(this, CpTestActivity::class.java))
            }
        }

        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_to_sign.setOnClickListener { startActivity(Intent(this, SignActivity::class.java)) }

        btn_to_multi.setOnClickListener { startActivity(Intent(this, MultiShapeActivity::class.java)) }

        btn_to_radar.setOnClickListener { startActivity(Intent(this, RadarActivity::class.java)) }

        btn_cp_test.setOnClickListener { startActivity(Intent(this, CpTestActivity::class.java)) }

        btn_notify.setOnClickListener { buildNotify() }

        btn_listener.setOnClickListener { openNotificationListenSettings() }

        btn_image.setOnClickListener { startActivity(Intent(this, ImageTestActivity::class.java)) }

        btn_list.setOnClickListener { startActivity(Intent(this, ListActivity::class.java)) }

        tv_show.text = globalConfig?.appName
    }


    private fun buildNotify() {
        var notificationService = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val view_custom = RemoteViews(packageName, R.layout.view_custom)
        view_custom.setTextViewText(R.id.tv_custom_title, "今日头条")
        view_custom.setTextViewText(R.id.tv_custom_content, "金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果。")

        var notification = Notification()

        notification.`when` = System.currentTimeMillis()
        notification.flags = Notification.FLAG_AUTO_CANCEL
        notification.tickerText = "hello world"
        notification.icon = R.mipmap.ic_launcher//这是个坑，如果不设置icon，通知不显示

        notification.contentView = view_custom
        var intent = Intent(this, CpTestActivity::class.java)
//        intent.`package` = packageName
        var pendingIntent = PendingIntent.getActivity(this, 200, intent, 0)
        notification.contentIntent = pendingIntent


        notificationService.notify(100, notification)

        // test branch

    }


    fun openNotificationListenSettings() {
        try {
            val intent: Intent
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            } else {
                intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }

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
