package com.va.kotlintaste.act

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.va.kotlintaste.R
import com.va.kotlintaste.fmt.FirstFragment
import kotlinx.android.synthetic.main.activity_bottom_tab_bar.*

class BottomTabBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_tab_bar)

        bottom_tab_bar.init(supportFragmentManager)
                .setImgSize(90f, 90f)
                .setFontSize(12f)
                .setTabPadding(4f, 6f, 10f)
                .setChangeColor(Color.GREEN, Color.RED)
                .addTabItem("one",R.drawable.ic_launcher_background,FirstFragment.javaClass)
                .addTabItem("two",R.drawable.ic_launcher_background,FirstFragment.javaClass)
                .addTabItem("three",R.drawable.ic_launcher_background,FirstFragment.javaClass)
                .setTabBarBackgroundColor(Color.BLACK)
                .isShowDivider(false)

    }
}
