package com.va.perfect.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    LinearLayout llEmptyView;

    LinearLayout llErrorView;

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

        llEmptyView = findViewById(R.id.ll_empty_data);

        llErrorView = findViewById(R.id.ll_error_data);

        setRecyclerConfig(recyclerView);

        mRecyclerAdapter = setAdapter();

        if (mRecyclerAdapter == null) {
            throw new NullPointerException(" adapter is null");
        }

        recyclerView.setAdapter(mRecyclerAdapter);

        initEvent();

        initDefault();

        needShowRefreshAnim = isNeedShowRefreshAnim();

        if (needShowRefreshAnim) {
            swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));
        }

        refreshData();

    }

    protected boolean isNeedShowRefreshAnim() {
        return true;
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

        swipeRefreshLayout.setOnRefreshListener(() -> pullRefreshData());

        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.setOnItemClickListener(this);
        }
    }

    private void clickRefreshData() {
        showClickRefreshView();
        refreshData();
    }

    private void showClickRefreshView() {

    }

    private void pullRefreshData() {
        refreshData();
    }

    protected void completeRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    protected void notifyDataSetChanged() {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.notifyDataSetChanged();
            if (mRecyclerAdapter.getItemCount() == 0) {
                showEmptyView();
            } else {
                hideEmptyView();
            }
        }
    }

    protected void showEmptyView() {
        getEmptyView().setVisibility(View.VISIBLE);
        getErrorDataView().setVisibility(View.GONE);
    }

    protected void hideEmptyView() {
        getEmptyView().setVisibility(View.GONE);
        getErrorDataView().setVisibility(View.GONE);
    }

    protected View getEmptyView() {
        return llEmptyView;
    }

    protected void showErrorDataView(Throwable throwable) {
        getErrorDataView().setVisibility(View.VISIBLE);
        setThrowableInfo(getErrorDataView(), throwable);
        getEmptyView().setVisibility(View.GONE);
    }

    private void setThrowableInfo(View errorDataView, Throwable throwable) {
        View tvErrorInfo = errorDataView.findViewById(R.id.tv_error_data);
        if (tvErrorInfo != null && tvErrorInfo instanceof TextView) {
            ((TextView) tvErrorInfo).setText(throwable.getMessage());
        }
    }

    protected void hideErrorDataView() {
        getErrorDataView().setVisibility(View.GONE);
        getEmptyView().setVisibility(View.GONE);
    }

    protected View getErrorDataView() {
        return llErrorView;
    }

    @Override
    public void onItemClick(int position) {

    }
}
