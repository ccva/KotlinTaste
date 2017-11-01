package com.va.perfect.wx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;
import com.va.perfect.net.dao.wx.WxChoiceListBean;

import java.util.List;

/**
 * Created by cjm on 17-11-1.
 */

public class WxChoiceAdapter extends BaseRecyclerAdapter<WxChoiceListBean.WxChoiceBean> {

    public WxChoiceAdapter(Context mContext, List<WxChoiceListBean.WxChoiceBean> dataList) {
        super(mContext, dataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wx_choice, parent, false));
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        TextView tvTitle = holder.itemView.findViewById(R.id.tv_title);
        TextView tvSource = holder.itemView.findViewById(R.id.tv_source);

        WxChoiceListBean.WxChoiceBean wxChoice = mDataList.get(position);
        tvTitle.setText(wxChoice.getTitle());
        tvSource.setText(wxChoice.getSource());
    }
}
