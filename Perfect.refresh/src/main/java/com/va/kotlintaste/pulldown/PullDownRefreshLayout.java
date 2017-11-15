package com.va.kotlintaste.pulldown;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @author Junmeng.Chen
 * @date 2017/11/14
 */

public class PullDownRefreshLayout extends ViewGroup implements PullDowmRefreshAble {

    public static final String TAG = PullDownRefreshLayout.class.getSimpleName();
    private Scroller mScroller;
    private float mLastMotionY;
    private VelocityTracker mVelocityTracker;

    public PullDownRefreshLayout(Context context) {
        this(context, null);
    }

    public PullDownRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PullDownRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Context context = getContext();
        mScroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mVelocityTracker == null) {
                    mVelocityTracker = VelocityTracker.obtain();
                    mVelocityTracker.addMovement(event);
                }
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float delt = mLastMotionY - y;
                if (isCanMove(delt)){
                    if (mVelocityTracker != null){
                        mVelocityTracker.addMovement(event);
                    }
                    scrollBy(0, (int) delt);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mVelocityTracker != null) {
                    mVelocityTracker.addMovement(event);
                    mVelocityTracker.computeCurrentVelocity(1000);
                    float yVelocity = mVelocityTracker.getYVelocity();

                }
                break;
            default:
                break;
        }
        mLastMotionY = y;
        return true;
    }

    private boolean isCanMove(float delt) {


        return false;
    }
}
