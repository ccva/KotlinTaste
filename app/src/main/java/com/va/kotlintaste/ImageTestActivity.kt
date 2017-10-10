package com.va.kotlintaste

import android.graphics.drawable.Animatable
import android.graphics.drawable.AnimatedVectorDrawable
import android.os.Bundle
import android.support.graphics.drawable.AnimatedVectorDrawableCompat
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_image_test.*

class ImageTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_test)

//        civ.setPreWidth(400F,2000F)
//
//        civ.scaleType = ImageView.ScaleType.CENTER_CROP
//
//        civ.setImageResource(R.drawable.timg)

        var vectorDrawable = iv.drawable as AnimatedVectorDrawableCompat

        iv.setOnClickListener {
            vectorDrawable.start()
        }


    }
}
