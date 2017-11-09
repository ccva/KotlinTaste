package com.va.perfect.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Junmeng.Chen
 * @date 2017/10/20
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    protected Context mContext;

    private boolean needLazyLoading = true;

    private boolean needLoadOnStart = true;

    public boolean isNeedLazyLoading() {
        return needLazyLoading;
    }

    public void setNeedLazyLoading(boolean needLazyLoading) {
        this.needLazyLoading = needLazyLoading;
    }

    public boolean isNeedLoadOnStart() {
        return needLoadOnStart;
    }

    public void setNeedLoadOnStart(boolean needLoadOnStart) {
        this.needLoadOnStart = needLoadOnStart;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflaterRootView(inflater, container, savedInstanceState);
        mRootView = rootView;
        initCreateViewDefault(savedInstanceState);
        return mRootView;
    }

    /**
     * 初始化方法 在onCreateView 方法中 将布局inflater 之后调用
     * @param savedInstanceState
     */
    protected abstract void initCreateViewDefault(Bundle savedInstanceState);

    /**
     * 设置 Fragment的填充视图
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    protected abstract View inflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    @Override
    public void onStart() {
        super.onStart();
        if (needLoadOnStart) {
            if (needLazyLoading) {
                if (getUserVisibleHint()) {
                    if (this instanceof BaseListFragment && ((BaseListFragment) this).isFirstLoad) {
                        ((BaseListFragment) this).onFirstLoad();
                        ((BaseListFragment) this).isFirstLoad = false;
                    } else {
                        refreshData();
                    }
                }
            } else {
                refreshData();
            }
        }
    }

    /**
     * 在onStart的生命周期 刷新数据
     */
    protected void refreshData() {

    }

}
