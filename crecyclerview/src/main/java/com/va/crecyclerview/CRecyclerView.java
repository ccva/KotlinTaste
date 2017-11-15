package com.va.crecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/14
 */

public class CRecyclerView extends RecyclerView {

    private View mEmptyView;

    private final RecyclerView.AdapterDataObserver mDataObserver = new DataObserver();
    private WrapAdapter mWrapAdapter;

    private boolean loadMoreEnable = false;

    public CRecyclerView(Context context) {
        super(context);
    }

    public CRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mWrapAdapter = new WrapAdapter(adapter);
        super.setAdapter(adapter);
        adapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }

    /**
     * DataObserver
     */
    class DataObserver extends AdapterDataObserver {
        public DataObserver() {
            super();
        }

        @Override
        public void onChanged() {
            super.onChanged();
            if (mWrapAdapter != null) {
                mWrapAdapter.notifyDataSetChanged();
            }
            if (mWrapAdapter != null && mEmptyView != null) {
                int emptyCount = 1 + mWrapAdapter.getHeadersCount();
                if (loadMoreEnable) {
                    emptyCount++;
                }
                if (mWrapAdapter.getItemCount() == emptyCount) {
                    mEmptyView.setVisibility(VISIBLE);
                    CRecyclerView.this.setVisibility(GONE);
                } else {
                    mEmptyView.setVisibility(GONE);
                    CRecyclerView.this.setVisibility(VISIBLE);
                }
            }
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            mWrapAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    /**
     * WrapAdapter
     */
    class WrapAdapter extends RecyclerView.Adapter<ViewHolder> implements HeadAble,
            WrapAble<RecyclerView.Adapter> {

        RecyclerView.Adapter mAdapter;

        public WrapAdapter(Adapter mAdapter) {
            this.mAdapter = mAdapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
            super.onBindViewHolder(holder, position, payloads);
        }

        @Override
        public int getItemCount() {
            return 0;
        }

        @Override
        public int getHeadersCount() {
            return 0;
        }

        @Override
        public void setWrapObject(Adapter adapter) {
            mAdapter = adapter;
        }

        @Override
        public Adapter getOriginalWrapObject() {
            return null;
        }
    }

    interface HeadAble {

        /**
         * 获取 Header 个数
         *
         * @return
         */
        int getHeadersCount();

    }

    interface WrapAble<T> {

        /**
         * 设置 包装对象
         *
         * @param t
         */
        void setWrapObject(T t);

        /**
         * 获取 原始包装对象
         *
         * @return
         */
        T getOriginalWrapObject();

    }

}
