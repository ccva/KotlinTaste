package com.va.kotlintaste.act

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.va.kotlintaste.R
import kotlinx.android.synthetic.main.activity_sign.*

class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)
    }

    fun clear(view: View) {
        signView.clear()
    }
}
