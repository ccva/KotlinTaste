package com.va.perfect.main;

import android.content.Context;
import android.view.ViewGroup;

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
        return null;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

    }
}
