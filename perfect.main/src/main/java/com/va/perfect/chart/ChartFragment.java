package com.va.perfect.chart;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    }

    @Override
    protected void initCreateViewDefault() {
        progressView.setOnClickListener(v -> startAnim());
    }

    private void startAnim() {
        ObjectAnimator progress = ObjectAnimator.ofFloat(progressView, "progress", 0f, 180f);

        progress.start();//启动动画。
    }
}
