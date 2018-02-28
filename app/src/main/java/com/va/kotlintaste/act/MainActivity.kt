package com.va.kotlintaste.act

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import com.va.kotlintaste.R
import com.va.kotlintaste.config.GlobalConfig
import com.va.kotlintaste.toast
import com.va.kotlintaste.util.ApplicationUtils
import com.va.kotlintaste.util.NotificationUtils
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

        btn_back.setOnClickListener{startActivity(Intent(this,BackActivity::class.java))}

        btn_jump_to_perfect_main.setOnClickListener { ApplicationUtils.doStartApplicationWithPackageName(this,"com.va.perfect") }

        btn_jump_to_bottom_act.setOnClickListener {
            startActivity(Intent(this,
                    BottomTabBarActivity::class.java))
        }

        tv_show.text = globalConfig?.appName
    }


    private fun buildNotify() {
        var title = "今日头条"
        var content = "金州勇士官方宣布球队已经解雇了主帅马克-杰克逊，随后宣布了最后的结果。"

        var intent = Intent(this, CpTestActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(this, 200, intent, 0)

        var notificationUtils = NotificationUtils(this)
        notificationUtils.sendNotification(title,content,pendingIntent)

    }


    private fun openNotificationListenSettings() {
        try {
            val intent: Intent = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
            } else {
                Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
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
