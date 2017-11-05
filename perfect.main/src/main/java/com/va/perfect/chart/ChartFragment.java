package com.va.perfect.chart;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.va.perfect.R;
import com.va.perfect.chart.view.ProgressView;
import com.va.perfect.fragment.BaseFragment;


/**
 * @author cjm
 */
public class ChartFragment extends BaseFragment {

    private ProgressView progressView;

    public static ChartFragment newInstance() {
        Bundle args = new Bundle();
        ChartFragment fragment = new ChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        progressView = view.findViewById(R.id.progress_view);
        progressView.setProgress(90f);
    }

    @Override
    protected void initCreateViewDefault() {
        progressView.setOnClickListener(v -> startAnim());
    }

    private void startAnim() {
//        ObjectAnimator alpha = ObjectAnimator.ofFloat(progressView, "alpha", 0f, 1f);
//        alpha.setDuration(10000);//设置动画时间
//        alpha.setInterpolator(new DecelerateInterpolator());//设置动画插入器，减速
//        alpha.setRepeatCount(-1);//设置动画重复次数，这里-1代表无限
//        alpha.setRepeatMode(ValueAnimator.REVERSE);//设置动画循环模式。
//        alpha.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                float value = (float) animation.getAnimatedValue();
//                Log.i("progressview", "startAnim: " + value);
//            }
//        });
//
//        alpha.start();//启动动画。

        ObjectAnimator animator = ObjectAnimator.ofFloat(progressView,"alpha",1,0,1);
        animator.setDuration(2000);
        animator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            Log.i("progressview", "startAnim: " + value);
        });
        animator.start();

    }
}
