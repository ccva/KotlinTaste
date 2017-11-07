package com.va.perfect.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * @author Junmeng.Chen
 * @date 2017/11/7
 */
public class MyListView extends ListView {

    public static final String TAG = MyListView.class.getSimpleName();

    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        Log.i(TAG, "dispatchTouchEvent: " + result);
        return result;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = super.onInterceptTouchEvent(ev);
        Log.i(TAG, "onInterceptTouchEvent: " + result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean result = super.onTouchEvent(ev);
        Log.i(TAG, "onTouchEvent: " + result);
        return result;
    }

    //    @Override
    //    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    //        // TODO Auto-generated method stub
    //        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
    //                View.MeasureSpec.AT_MOST);
    //        super.onMeasure(widthMeasureSpec, expandSpec);
    //
    //    }
    //
    //    @Override
    //    public boolean performClick() {
    //        return super.performClick();
    //    }
}
