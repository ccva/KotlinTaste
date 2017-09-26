package com.va.kotlintaste.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;

/**
 * Created by Junmeng.Chen on 2017/9/26.
 */

public class CustomImageView extends android.support.v7.widget.AppCompatImageView {

    float minWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());

    float maxHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());

    float preWidth = 0;

    float preHeight = 0;

    public void setPreWidth(float preWidth, float preHeight) {
        this.preWidth = preWidth;
        this.preHeight = preHeight;
    }

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        float i = preWidth / preHeight;

        if (i < 0.4) {

            widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) minWidth, MeasureSpec.EXACTLY);

            heightMeasureSpec = MeasureSpec.makeMeasureSpec((int) maxHeight, MeasureSpec.EXACTLY);

        } else {

        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
