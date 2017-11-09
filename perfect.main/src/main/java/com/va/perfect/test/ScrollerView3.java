package com.va.perfect.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author Junmeng.Chen
 * @date 2017/11/8
 */

public class ScrollerView3 extends ScrollView {

    public static final String TAG = ScrollerView3.class.getSimpleName();

    public ScrollerView3(Context context) {
        super(context);
    }

    public ScrollerView3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollerView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.i(TAG, "dispatchTouchEvent: event action is " + ev.getAction() + " result " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        boolean result = super.onTouchEvent(ev);

        Log.i(TAG, "onTouchEvent: event action is " + ev.getAction() + " result " + result);

        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            return false;
        } else {
            return true;
        }
    }
}
