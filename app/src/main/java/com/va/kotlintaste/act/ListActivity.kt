package com.va.kotlintaste.act

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.BaseAdapter
import com.va.kotlintaste.R
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_CONTENT_TRANSITIONS)
        setContentView(R.layout.activity_list)

        iv.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(Intent(this, TransateActivity::class.java),
                        ActivityOptions.makeSceneTransitionAnimation(this, iv, "cjm").toBundle())
            } else {
                startActivity(Intent(this, TransateActivity::class.java))
            }
        }

//        var data: MutableList<String> = ArrayList()
//
//        (0 until 5).mapTo(data) { it.toString() }
//
//        var tAdapter = TAdapter()
//        tAdapter.mData = data
//        lv.adapter = tAdapter
//
//        lv.onItemClickListener = AdapterView.OnItemClickListener { _, view, position, id ->
//            run {
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    startActivity(Intent(this, TransateActivity::class.java),
//                            ActivityOptions.makeSceneTransitionAnimation(this, view, "cjm").toBundle())
//                } else {
//                    startActivity(Intent(this, TransateActivity::class.java))
//                }
//            }
//        }

    }


    class TAdapter : BaseAdapter() {

        private var mData: List<String>? = null
            set(value) {
                field = value
            }
            get() {
                return field
            }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            return LayoutInflater.from(parent?.context).inflate(R.layout.item_list, null)
        }

        override fun getItem(position: Int): Any {
            return position
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return mData?.size!!
        }

    }

}
