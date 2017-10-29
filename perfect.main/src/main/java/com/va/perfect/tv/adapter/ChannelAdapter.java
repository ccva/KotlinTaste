package com.va.perfect.tv.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;
import com.va.perfect.net.dao.result.CategoryDao;
import com.va.perfect.net.dao.result.ChannelDao;

import java.util.List;

/**
 * Created by cjm on 17-10-30.
 */

public class ChannelAdapter extends BaseRecyclerAdapter<ChannelDao.ChannelBean> {

    public ChannelAdapter(Context mContext, List<ChannelDao.ChannelBean> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(mLayoutInflater.inflate(R.layout.item_category, null));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        TextView tvName = holder.itemView.findViewById(R.id.tv_name);

        ChannelDao.ChannelBean categoryBean = mDataList.get(position);
        tvName.setText(categoryBean.getChannelName());
    }
}
