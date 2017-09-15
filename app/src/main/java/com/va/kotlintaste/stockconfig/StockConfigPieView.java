package com.va.kotlintaste.stockconfig;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Junmeng.Chen on 2017/9/12.
 */

public class StockConfigPieView extends View {

    String str = "股票配资";

    public StockConfigPieView(Context context) {
        super(context);
    }

    public StockConfigPieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StockConfigPieView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();

        int centerX = width / 2;
        int centerY = centerX;

        int radius = centerX / 2;

        drawText(canvas, centerX, centerY);

        drawBackgroundCircle(canvas, centerX, centerY, radius);


        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(100);
        paint.setAntiAlias(true);

        RectF rect = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

        canvas.drawArc(rect, 0, 91, false, paint);
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rect, 90, 91, false, paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rect, 180, 91, false, paint);
        paint.setColor(Color.GREEN);
        canvas.drawArc(rect, 270, 91, false, paint);

    }

    private void drawBackgroundCircle(Canvas canvas, int centerX, int centerY, int radius) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(98);

        canvas.drawCircle(centerX, centerY, radius, paint);
    }

    private void drawText(Canvas canvas, int centerX, int centerY) {
        TextPaint textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.RED);
        textPaint.setTextSize(60);

        Point point = new Point(centerX, centerY);

        textCenter(str, textPaint, canvas, point, 150, Layout.Alignment.ALIGN_CENTER, 0.9f, 0, false);
    }

    private void textCenter(String string,
                            TextPaint textPaint,
                            Canvas canvas,
                            Point point,
                            int width,
                            Layout.Alignment align,
                            float spacingmult,
                            float spacingadd,
                            boolean includepad) {
        StaticLayout staticLayout = new StaticLayout(string, textPaint, width, align, spacingmult, spacingadd, includepad);
        canvas.save();
        canvas.translate(-staticLayout.getWidth() / 2 + point.x, -staticLayout.getHeight() / 2 + point.y);
        staticLayout.draw(canvas);
        canvas.restore();
    }
}
