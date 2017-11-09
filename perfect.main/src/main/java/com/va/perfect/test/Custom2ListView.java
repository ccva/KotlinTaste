package com.va.perfect.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * @author Junmeng.Chen
 * @date 2017/11/8
 */

public class Custom2ListView extends ListView {

    public static final String TAG = Custom2ListView.class.getSimpleName();

    public Custom2ListView(Context context) {
        super(context);
    }

    public Custom2ListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Custom2ListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.i(TAG, "dispatchTouchEvent: event action is " + ev.getAction() + " result " + result);
        return result;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        Log.i(TAG, "onInterceptTouchEvent: event action is " + ev.getAction() + " result " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        boolean result = super.onTouchEvent(ev);

        Log.i(TAG, "onTouchEvent: event action is " + ev.getAction() + " result " + result);

        return result;
    }

}
