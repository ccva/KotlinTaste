package com.va.kotlintaste.act

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.va.kotlintaste.R
import kotlinx.android.synthetic.main.activity_mutli_shape.*

class MultiShapeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mutli_shape)

        btn_ok.setOnClickListener {

            var text = edt.text

            var i = text.toString().toIntOrNull()

            msv.setMulti(i!!)

        }
    }
}
