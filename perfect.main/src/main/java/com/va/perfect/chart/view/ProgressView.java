package com.va.perfect.chart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.va.perfect.R;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 */

public class ProgressView extends View {

    private float mScale = 1f;

    private float mDegree = 90f;

    private String mDegreeString = "0%";

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int preWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int preHeightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (preWidthSize > preHeightSize) {
            preWidthSize = preHeightSize;
        } else {
            preHeightSize = preWidthSize;
        }
        mScale = preWidthSize / 1080f;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(preWidthSize, MeasureSpec.getMode(widthMeasureSpec));
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(preHeightSize, MeasureSpec.getMode(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawColor(Color.parseColor("#ffffff"));

        super.onDraw(canvas);

        calc();

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeCap(Paint.Cap.ROUND);

        paint.setStrokeWidth(80 * mScale);

        paint.setColor(Color.parseColor("#aa000000"));

        RectF rectF = new RectF(getLeft() + getPaddingLeft(), getTop() + getPaddingTop(),
                getRight() - getPaddingRight(), getBottom() - getPaddingBottom());

        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2, paint);

        paint.setColor(getResources().getColor(R.color.colorAccent));

        Path path = new Path();

        path.addArc(rectF, 0, mDegree);

        canvas.drawPath(path, paint);

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint.setColor(Color.BLUE);

        textPaint.setTextSize(120 * mScale);

        float textLength = textPaint.measureText(mDegreeString);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        float startX = (getLeft() + getRight()) / 2 - textLength / 2;

        float startY = (getTop() + getBottom()) / 2 + (-fontMetrics.ascent - fontMetrics.descent) / 2;

        canvas.drawText(mDegreeString, startX, startY, textPaint);
    }

    private void calc() {

        mDegreeString = String.format("%.2f", mDegree) + "%";

    }
}
