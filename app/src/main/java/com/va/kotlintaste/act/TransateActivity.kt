package com.va.kotlintaste.act

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.app.AppCompatActivity
import android.transition.Fade
import android.view.Window
import com.va.kotlintaste.R

class TransateActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS)
//        var transition = TransitionInflater.from(this).inflateTransition(R.transition.slide)
//        window.exitTransition = transition
        window.enterTransition = Fade()
//        window.reenterTransition = transition
        setContentView(R.layout.activity_transate)
    }
}
