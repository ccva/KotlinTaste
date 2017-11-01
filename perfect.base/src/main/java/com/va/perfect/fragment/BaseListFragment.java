package com.va.perfect.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/10/31
 */

public abstract class BaseListFragment<T> extends BaseFragment implements BaseRecyclerAdapter.OnItemClickListener {

    SwipeRefreshLayout swipeRefreshLayout;

    RecyclerView recyclerView;

    protected BaseRecyclerAdapter<T> mRecyclerAdapter;

    protected List<T> mDataList = new ArrayList<>();

    private boolean needShowRefreshAnim = true;

    public boolean isFirstLoad = true;

    @Override
    protected View inflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base_list, container, false);
        initView(view);

        initList();

        return view;
    }

    private void initView(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
    }

    private void initList() {

        setRecyclerConfig(recyclerView);

        mRecyclerAdapter = setAdapter();

        if (mRecyclerAdapter == null) {
            throw new NullPointerException(" adapter is null");
        }

        recyclerView.setAdapter(mRecyclerAdapter);

        initEvent();

        initDefault();

    }

    private void initDefault() {

    }

    protected boolean isNeedShowRefreshAnim() {
        return true;
    }

    public void onFirstLoad() {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
        refreshData();
    }

    /**
     * 配置 RecyclerView
     * @param recyclerView
     */
    protected abstract void setRecyclerConfig(RecyclerView recyclerView);

    /**
     * 设置 RecyclerView适配器
     *
     * @return
     */
    protected abstract BaseRecyclerAdapter<T> setAdapter();

    private void initEvent() {
        swipeRefreshLayout.setOnRefreshListener(() -> refreshData());

        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.setOnItemClickListener(this);
        }
    }

    protected void completeRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    protected void notifyDataSetChanged() {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(int position) {

    }
}
