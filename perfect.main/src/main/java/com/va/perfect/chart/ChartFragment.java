package com.va.perfect.chart;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.va.perfect.R;
import com.va.perfect.fragment.BaseFragment;


/**
 * @author cjm
 */
public class ChartFragment extends BaseFragment {

    public static ChartFragment newInstance() {
        Bundle args = new Bundle();
        ChartFragment fragment = new ChartFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart, container, false);
    }

    @Override
    protected void initCreateViewDefault() {

    }
}
