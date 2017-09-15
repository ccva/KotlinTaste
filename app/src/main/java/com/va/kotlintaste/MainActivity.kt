package com.va.kotlintaste

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_to_sign.setOnClickListener { startActivity(Intent(this, SignActivity::class.java)) }

        btn_to_multi.setOnClickListener { startActivity(Intent(this, MultiShapeActivity::class.java)) }

        btn_to_radar.setOnClickListener { startActivity(Intent(this, RadarActivity::class.java)) }

        btn_cp_test.setOnClickListener { startActivity(Intent(this, CpTestActivity::class.java)) }
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
