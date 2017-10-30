package com.va.perfect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/10/30
 */

public abstract class BaseListActivity<T> extends BaseActivity implements BaseRecyclerAdapter.OnItemClickListener {

    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    protected BaseRecyclerAdapter<T> mRecyclerAdapter;

    protected List<T> mDataList = new ArrayList<>();

    private boolean needShowRefreshAnim = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_list);

        getResult(getIntent());

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        recyclerView = findViewById(R.id.recycler_view);

        setRecyclerConfig(recyclerView);

        mRecyclerAdapter = setAdapter();

        if (mRecyclerAdapter == null) {
            throw new NullPointerException(" adapter is null");
        }

        recyclerView.setAdapter(mRecyclerAdapter);

        initEvent();

        initDefault();

        if (needShowRefreshAnim) {
            swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
        }

        refreshData();

    }

    protected void refreshData() {
    }

    protected void getResult(Intent intent) {

    }

    protected void initDefault() {

    }

    /**
     * 设置 RecyclerView 配置
     *
     * @param recyclerView
     */
    protected abstract void setRecyclerConfig(RecyclerView recyclerView);

    /**
     * 设置 适配器
     *
     * @return
     */
    protected abstract BaseRecyclerAdapter setAdapter();

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
