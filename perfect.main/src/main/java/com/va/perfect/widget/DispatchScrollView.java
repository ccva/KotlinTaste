package com.va.perfect.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author Junmeng.Chen
 * @date 2017/11/6
 */

public class DispatchScrollView extends ScrollView {

    private OnInterceptTouchJudge onInterceptTouchJudge;

    public void setOnInterceptTouchJudge(OnInterceptTouchJudge onInterceptTouchJudge) {
        this.onInterceptTouchJudge = onInterceptTouchJudge;
    }

    public DispatchScrollView(Context context) {
        super(context);
    }

    public DispatchScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DispatchScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //        if (super.onInterceptTouchEvent(ev)) {
        //            return true;
        //        }
        //
        //        if (onInterceptTouchJudge != null) {
        //            return onInterceptTouchJudge.onInterceptTouchJudge(ev);
        //        }
        //
                return super.onInterceptTouchEvent(ev);

//        return false;
    }

    public interface OnInterceptTouchJudge {

        /**
         * 判断
         *
         * @param ev
         * @return
         */
        boolean onInterceptTouchJudge(MotionEvent ev);
    }

}
