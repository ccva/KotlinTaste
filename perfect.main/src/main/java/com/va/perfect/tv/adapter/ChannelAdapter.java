package com.va.perfect.tv.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;
import com.va.perfect.net.dao.tv.ChannelBean;

import java.util.List;

/**
 *
 * @author cjm
 * @date 17-10-30
 */

public class ChannelAdapter extends BaseRecyclerAdapter<ChannelBean> {

    public ChannelAdapter(Context mContext, List<ChannelBean> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(mLayoutInflater.inflate(R.layout.item_category, parent,false));
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        TextView tvName = holder.itemView.findViewById(R.id.tv_name);

        ChannelBean categoryBean = mDataList.get(position);
        tvName.setText(categoryBean.getChannelName());
    }
}
