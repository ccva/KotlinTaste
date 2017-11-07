package com.va.perfect.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author Junmeng.Chen
 * @date 2017/11/7
 */

public class MyScrollerView extends ScrollView {

    public static final String TAG = MyScrollerView.class.getSimpleName();

    private int yDown;

    private int yMove;

    private int mLastMove;

    private EventClash eventClash;

    public void setEventClash(EventClash eventClash) {
        this.eventClash = eventClash;
    }

    public MyScrollerView(Context context) {
        super(context);
    }

    public MyScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int y = (int) ev.getY();
        boolean isIntercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                yDown = y;
                isIntercept = false;
                break;
            case MotionEvent.ACTION_MOVE:
                yMove = y;
                if (mLastMove == 0) {
                    mLastMove = yDown;
                }

                int moveDirection = yMove - mLastMove;

                mLastMove = y;

                if (eventClash != null) {

                    if (eventClash.judgeWindowRight()) {
                        //悬浮窗体到达指定位置，进行下一步判断

                        // 判断 listView 是否滑动到第一条，如果没有滑动到第一条，则 事件交由ListView处理，否则进行进一步判断
                        if (eventClash.judgeListViewOnTop()) {
                            if (moveDirection < 0) {
                                //如果是 向上滑动的情况，则 事件交由listView处理
                                // listView 滑动 请求父布局 不拦截
                                isIntercept = false;
                            } else {
                                // 如果是 向下滑动的情况，则 事件交由ScrollView 处理
                                // scrollView 滑动 请求父布局 拦截
                                isIntercept = true;
                            }
                        } else {
                            // listView 没有滑动到 第一条，事件交由 listView 处理
                            // listView 滑动 请求父布局 不拦截
                            isIntercept = false;
                        }
                    } else {
                        //悬浮窗体没有到达指定位置，事件交由ScrollView 处理
                        // scrollView 滑动 请求父布局 拦截
                        isIntercept = true;
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                isIntercept = false;
                break;
            default:
                break;
        }
        //返回true表示拦截，返回false表示不拦截
        return isIntercept;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    public interface EventClash {

        /**
         * 判断悬浮的窗体位置 是否到位
         *
         * @return
         */
        boolean judgeWindowRight();

        /**
         * 判断 滑动控件是否到顶
         * @return
         */
        boolean judgeListViewOnTop();

    }


}
