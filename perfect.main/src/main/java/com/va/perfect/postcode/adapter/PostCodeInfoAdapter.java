package com.va.perfect.postcode.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;
import com.va.perfect.net.dao.postcode.PostCodeInfo;

import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/10
 */

public class PostCodeInfoAdapter extends BaseRecyclerAdapter<PostCodeInfo.PostCodeBean> {

    public PostCodeInfoAdapter(Context mContext, List<PostCodeInfo.PostCodeBean> dataList) {
        super(mContext, dataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_post_code_info, parent, false);
        return new BaseRecyclerViewHolder(dataBinding);
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        ViewDataBinding dataBinding = holder.getViewDataBinding();
        dataBinding.setVariable(BR.postcodeInfo, mDataList.get(position));
    }
}
