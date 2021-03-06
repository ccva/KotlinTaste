package com.va.perfect.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;

import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/10/30
 */

public class MainMenuAdapter extends BaseRecyclerAdapter<String> {

    public MainMenuAdapter(Context mContext, List<String> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_menu, parent, false);
        BaseRecyclerViewHolder viewHolder = new BaseRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        String name = mDataList.get(position);
        TextView tv = holder.itemView.findViewById(R.id.tv_name);
        tv.setText(name);
    }
}
