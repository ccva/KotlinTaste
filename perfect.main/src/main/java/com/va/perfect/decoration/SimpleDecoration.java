package com.va.perfect.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.va.perfect.R;

/**
 * @author Junmeng.Chen
 * @date 2017/11/16
 */

public class SimpleDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;

    private int bottomDividerHeight;

    private int topDividerHeight;

    private int leftDividerWidth;

    private int rightDividerWidth;

    private Paint dividerPaint;

    private int dividerMarginRight;

    private int dividerMarginLeft;
    private final int mBottomDividerColor;

    public SimpleDecoration(Context context) {
        mContext = context;
        dividerPaint = new Paint();
        mBottomDividerColor = context.getResources().getColor(R.color.colorAccent);

        bottomDividerHeight = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        topDividerHeight = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        leftDividerWidth = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
        rightDividerWidth = context.getResources().getDimensionPixelSize(R.dimen.divider_height);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        super.getItemOffsets(outRect, view, parent, state);
        //类似加了一个bottom padding
        outRect.bottom = bottomDividerHeight;
        outRect.top = topDividerHeight;
        outRect.left = leftDividerWidth;
        outRect.right = rightDividerWidth;
    }

    public void setDividerMarginRight(@DimenRes int dividerMarginRight) {
        this.dividerMarginRight = mContext.getResources().getDimensionPixelSize(dividerMarginRight);
    }

    public void setDividerMarginLeft(@DimenRes int dividerMarginLeft) {
        this.dividerMarginLeft = mContext.getResources().getDimensionPixelSize(dividerMarginLeft);
    }

    /**
     * 在 item 绘制之前绘制
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {


    }

    /**
     * 在 item 绘制之后绘制
     *
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int childCount = parent.getChildCount();
        int left = 0 + parent.getPaddingLeft() + dividerMarginLeft;
        int right = 0 + parent.getWidth() - parent.getPaddingRight() - dividerMarginRight;

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);

            drawLeftDividerRect(c, left, view);

            drawTopDividerRect(c, left, right, view);

            drawRightDividerRect(c, right, view);

            drawBottomDividerRect(c, left, right, view);
        }

    }

    private void drawLeftDividerRect(Canvas c, int left, View view) {
        dividerPaint.setColor(Color.YELLOW);
        c.drawRect(left, view.getTop() - topDividerHeight, left + leftDividerWidth, view
                .getBottom(), dividerPaint);
    }

    private void drawTopDividerRect(Canvas c, int left, int right, View view) {
        dividerPaint.setColor(Color.GREEN);
        c.drawRect(left + leftDividerWidth, view.getTop() - topDividerHeight, right, view.getTop
                (), dividerPaint);
    }

    private void drawRightDividerRect(Canvas c, int right, View view) {
        dividerPaint.setColor(Color.BLUE);
        c.drawRect(right - rightDividerWidth, view.getTop(), right, view.getBottom() +
                bottomDividerHeight, dividerPaint);
    }

    private void drawBottomDividerRect(Canvas c, int left, int right, View view) {
        float top = view.getBottom();
        float bottom = view.getBottom() + bottomDividerHeight;
        dividerPaint.setColor(mBottomDividerColor);
        c.drawRect(left, top, right - rightDividerWidth, bottom, dividerPaint);
    }
}
