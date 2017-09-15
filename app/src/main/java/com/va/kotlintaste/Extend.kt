package com.va.kotlintaste

import android.content.Context
import android.widget.Toast

/**
 * Created by Junmeng.Chen on 2017/9/13.
 */
fun Math.angleToRadian(angle: Int): Double {
    return angle * Math.PI / 180
}

fun Context.toast(msg:String,length:Int = Toast.LENGTH_SHORT){
    Toast.makeText(this,msg,length).show()
}