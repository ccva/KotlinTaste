package com.va.perfect.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * @author Junmeng.Chen
 * @date 2017/11/7
 */

public class ListViewInScrollerView extends ListView {

    public static final String TAG = ListViewInScrollerView.class.getSimpleName();

    private float mDownX;
    private float mDownY;
    private double mLastMove;

    private EventClash eventClash;

    public void setEventClash(EventClash eventClash) {
        this.eventClash = eventClash;
    }


    public ListViewInScrollerView(Context context) {
        super(context);
    }

    public ListViewInScrollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewInScrollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        boolean result = super.onTouchEvent(ev);

        Log.i(TAG, "onTouchEvent: event action is " + ev.getAction() + " result " + result);

        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                //ACTION_DOWN的时候，赶紧把事件hold住
                eventClash.getScrollView().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float yMove = event.getY();

                if (mLastMove == 0) {
                    mLastMove = mDownY;
                }

                double moveDirection = yMove - mLastMove;

                mLastMove = yMove;

                if (eventClash != null) {

                    if (eventClash.judgeWindowRight()) {
                        //悬浮窗体到达指定位置，进行下一步判断

                        // 判断 listView 是否滑动到第一条，如果没有滑动到第一条，则 事件交由ListView处理，否则进行进一步判断
                        if (eventClash.judgeListViewOnTop()) {
                            if (moveDirection < 0) {
                                //如果是 向上滑动的情况，则 事件交由listView处理
                                // listView 滑动 请求父布局 不拦截
//                                getParent().requestDisallowInterceptTouchEvent(true);
                            } else {
                                // 如果是 向下滑动的情况，则 事件交由ScrollView 处理
                                // scrollView 滑动 请求父布局 拦截
                                eventClash.getScrollView().requestDisallowInterceptTouchEvent(false);
                            }
                        } else {
                            // listView 没有滑动到 第一条，事件交由 listView 处理
                            // listView 滑动 请求父布局 不拦截
//                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                    } else {
                        //悬浮窗体没有到达指定位置，事件交由ScrollView 处理
                        // scrollView 滑动 请求父布局 拦截
                        eventClash.getScrollView().requestDisallowInterceptTouchEvent(false);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //其实这里是多余的
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(event);
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

        ScrollView getScrollView();

    }
}
