package com.va.kotlintaste.stockconfig;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Junmeng.Chen on 2017/9/13.
 */

public class MultiLineText extends View {

    private Paint mPaint;

    public MultiLineText(Context context) {
        super(context);
    }

    public MultiLineText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiLineText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
        super.onDraw(canvas);

        int width = getWidth();

        int height = getHeight();
//
//        mPaint = new Paint();
//
//        mPaint.setTextSize(50);
//        mPaint.setColor(Color.BLUE);
//        String[] strings = new String[]{"Idtk", "是", "一", "个", "小", "学", "生"};
//        Point point = new Point(width >> 1, height >> 1);
//        textCenter(strings, mPaint, canvas, point, Paint.Align.CENTER);

        String mString = "Idtk是一个小学生";
        TextPaint tp = new TextPaint();
        tp.setColor(Color.BLUE);
        tp.setStyle(Paint.Style.FILL);
        tp.setTextSize(50);
        Point point = new Point(width >> 1, height >> 1);
        textCenter(mString, tp, canvas, point, 150, Layout.Alignment.ALIGN_CENTER, 1.5f, 0, false);

    }

//    private void textCenter(String[] strings, Paint paint, Canvas canvas, Point point, Paint.Align align) {
//        mPaint.setTextAlign(align);
//        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
//        float top = fontMetrics.top;
//        float bottom = fontMetrics.bottom;
//        int length = strings.length;
//        float total = (length - 1) * (-top + bottom) + (-fontMetrics.ascent + fontMetrics.descent);
//        float offset = total / 2 - bottom;
//        for (int i = 0; i < length; i++) {
//            float yAxis = -(length - i - 1) * (-top + bottom) + offset;
//            canvas.drawText(strings[i], point.x, point.y + yAxis, mPaint);
//        }
//    }

    private void textCenter(String string, TextPaint textPaint, Canvas canvas, Point point, int width, Layout.Alignment align, float spacingmult, float spacingadd, boolean includepad) {
        StaticLayout staticLayout = new StaticLayout(string, textPaint, width, align, spacingmult, spacingadd, includepad);
        canvas.save();
        canvas.translate(-staticLayout.getWidth() / 2 + point.x, -staticLayout.getHeight() / 2 + point.y);
        staticLayout.draw(canvas);
        canvas.restore();
    }

}
