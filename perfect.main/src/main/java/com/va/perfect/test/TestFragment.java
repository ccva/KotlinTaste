package com.va.perfect.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.va.perfect.R;
import com.va.perfect.fragment.BaseFragment;

/**
 * @author Junmeng.Chen
 * @date 2017/11/6
 */

public class TestFragment extends BaseFragment {

    private Button btnTest;

    public static TestFragment newInstance() {

        Bundle args = new Bundle();

        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    protected void initCreateViewDefault(Bundle savedInstanceState) {
        initView();
        initEvent();
    }

    private void initView() {
        btnTest = mRootView.findViewById(R.id.btn_test);

        mRootView.findViewById(R.id.btn_test2).setOnClickListener(v -> startActivity(new Intent(getActivity(), Test2Activity.class)));
        mRootView.findViewById(R.id.btn_test3).setOnClickListener(v -> startActivity(new Intent(getActivity(), Test3Activity.class)));
    }

    private void initEvent() {
        btnTest.setOnClickListener(v -> startActivity(new Intent(getActivity(), TestActivity.class)));
    }
}
