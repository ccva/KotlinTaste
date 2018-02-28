package com.va.kotlintaste.act

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.va.kotlintaste.R

class RecyclerDemoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_demo)
    }

    companion object {

        fun launch(context: Context) {
            val intent = Intent(context, RecyclerDemoActivity::class.java)
            context.startActivity(intent)
        }
    }
}
