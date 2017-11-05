package com.va.perfect.chart.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.va.perfect.R;

import java.math.BigDecimal;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 */

public class ProgressView extends View {

    public static final String TAG = "progressview";

    private float mScale = 1f;

    private float progress = 0f;

    private String mDegreeString = "0%";

    private int progressBackgroundColor = Color.parseColor("#aa000000");

    private int progressFoundColor = Color.parseColor("#ff4081");

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        progressBackgroundColor = typedArray.getColor(R.styleable.ProgressView_progressBackgroundColor, progressBackgroundColor);
        progressFoundColor = typedArray.getColor(R.styleable.ProgressView_progressColor, progressFoundColor);
        progress = typedArray.getFloat(R.styleable.ProgressView_progress, progress);
        typedArray.recycle();
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
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Log.i(TAG, "onDraw: " + progress);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeCap(Paint.Cap.ROUND);

        float stockWidth = 80 * mScale;

        paint.setStrokeWidth(stockWidth);

        float halfStockWidth = stockWidth / 2;

        RectF rectF = new RectF(getLeft() + getPaddingLeft() + halfStockWidth, getTop() + getPaddingTop() + halfStockWidth, getRight() - getPaddingRight() - halfStockWidth, getBottom() - getPaddingBottom() - halfStockWidth);

        drawProgressBackground(canvas, paint, rectF);

        drawProgress(canvas, paint, rectF);

        Paint textPaint = initTextPaintSetting();

        drawCenterText(canvas, textPaint);
    }

    @NonNull
    private Paint initTextPaintSetting() {
        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        textPaint.setColor(Color.BLUE);

        textPaint.setTextSize(120 * mScale);

        textPaint.setFakeBoldText(true);
        return textPaint;
    }

    private void drawCenterText(Canvas canvas, Paint textPaint) {
        calcText();

        float textLength = textPaint.measureText(mDegreeString);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        float startX = (getLeft() + getRight()) / 2 - textLength / 2;

        float startY = (getTop() + getBottom()) / 2 + (-fontMetrics.ascent - fontMetrics.descent) / 2;

        canvas.drawText(mDegreeString, startX, startY, textPaint);
    }

    private void drawProgress(Canvas canvas, Paint paint, RectF rectF) {
        paint.setColor(progressFoundColor);

        canvas.drawArc(rectF, 0, progress, false, paint);
    }

    private void drawProgressBackground(Canvas canvas, Paint paint, RectF rectF) {
        paint.setColor(progressBackgroundColor);

        canvas.drawCircle(rectF.centerX(), rectF.centerY(), rectF.width() / 2, paint);
    }

    private void calcText() {
        float percent = progress / 360f;
        BigDecimal b = new BigDecimal(percent * 100);
        int result = b.setScale(2, BigDecimal.ROUND_HALF_UP).intValue();

        mDegreeString = result + "%";
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }
}
