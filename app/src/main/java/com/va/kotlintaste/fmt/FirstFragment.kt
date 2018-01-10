package com.va.kotlintaste.fmt


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.va.kotlintaste.R


/**
 * A simple [Fragment] subclass.
 */
class FirstFragment : Fragment() {

    var params1:String? = null

    var params2:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            params1 = arguments.getString(ARG_PARAM1)
            params2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    companion object {

        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        // The request code must be 0 or greater.
        private val PLUS_ONE_REQUEST_CODE = 0

        fun newInstance(params1:String,params2:String):FirstFragment{
            val fragment = FirstFragment()
            val bundle = Bundle()
            bundle.putString(ARG_PARAM1,params1)
            bundle.putString(ARG_PARAM2,params2)
            fragment.arguments = bundle
            return fragment
        }
    }

}// Required empty public constructor

