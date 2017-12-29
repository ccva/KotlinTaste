package com.va.kotlintaste.sign;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Junmeng.Chen on 2017/9/13.
 */

public class SignView extends View {

    private Paint paint;

    private Path path;

    private int prex, prey;

    private Bitmap bitmapBuffer;

    private Canvas bitmapCanvas;

    public SignView(Context context) {
        this(context, null);
    }

    public SignView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("NewApi")
    public SignView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (bitmapBuffer == null) {
            bitmapBuffer = Bitmap.createBitmap(getMeasuredWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            bitmapCanvas = new Canvas(bitmapBuffer);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmapBuffer, 0, 0, null);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                prex = x;
                prey = y;
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.quadTo((prex + x) >> 1, (prey + y) >> 1, x, y);
                invalidate();
                prex = x;
                prey = y;
                break;
            case MotionEvent.ACTION_UP:
                bitmapCanvas.drawPath(path, paint);
                invalidate();
                break;
        }
        return true;
    }

    public void clear() {
        bitmapBuffer = Bitmap.createBitmap(getMeasuredWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmapBuffer);
        path.reset();
        invalidate();

        /*branch2*/
    }
}
