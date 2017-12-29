package com.va.kotlintaste.multiShape;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Junmeng.Chen on 2017/9/13.
 */

public class MultiShareView extends View {

    private int multi = 5;

    private Paint paint;

    private Paint grayPaint;

    private int radius;

    private int centerX, centerY;

    public MultiShareView(Context context) {
        this(context, null);
    }

    public MultiShareView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiShareView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = getMeasuredWidth() >> 1;
        centerY = centerX;
        radius = getMeasuredWidth() >> 2;
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStrokeCap(Paint.Cap.ROUND);

        grayPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        grayPaint.setColor(Color.GRAY);
        grayPaint.setStrokeWidth(4);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i("cjm","onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.i("cjm","onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {

        Log.i("cjm","onDraw");

        super.onDraw(canvas);

        float angle = (float) 360 / multi;

        double radian = getRadian(angle / 2);

        double halfLength = (radius * Math.sin(radian));

        double dis = radius * Math.cos(radian);

        canvas.drawPoint(centerX, centerY, paint);

        canvas.save();

        for (int i = 0; i < multi; i++) {
            canvas.drawLine(centerX, centerY, ((float) (centerX + dis)), ((float) (centerY - halfLength)), grayPaint);
            canvas.drawLine(((float) (centerX + dis)), ((float) (centerY - halfLength)), ((float) (centerX + dis)), ((float) (centerY + halfLength)), paint);
            canvas.rotate(angle, centerX, centerY);
        }

        canvas.restore();
    }

    public double getRadian(float angle) {
        return 2 * (Math.PI / 360) * angle;
    }

    public void setMulti(@Nullable int multi) {
        this.multi = multi;
        postInvalidate();

        /*branch3*/
    }
}
