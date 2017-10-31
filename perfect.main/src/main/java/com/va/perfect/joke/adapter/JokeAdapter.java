package com.va.perfect.joke.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;
import com.va.perfect.net.dao.joke.JokeBean;

import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/10/31
 */

public class JokeAdapter extends BaseRecyclerAdapter<JokeBean> {

    public JokeAdapter(Context mContext, List<JokeBean> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke, parent, false));
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        JokeBean jokeBean = mDataList.get(position);
        TextView tvContent = holder.itemView.findViewById(R.id.tv_content);
        tvContent.setText(jokeBean.getContent());
    }
}
