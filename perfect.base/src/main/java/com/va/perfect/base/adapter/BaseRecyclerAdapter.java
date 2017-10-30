package com.va.perfect.base.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 *
 * @author cjm
 * @date 17-10-29
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected Context mContext;

    protected List<T> mDataList;

    protected LayoutInflater mLayoutInflater;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BaseRecyclerAdapter(Context mContext, List<T> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 创建 ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public abstract BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    public boolean isMutliItemViewType() {
        return false;
    }

    public interface OnItemClickListener {

        /**
         * itemView 点击事件
         *
         * @param position
         */
        void onItemClick(int position);
    }
}
