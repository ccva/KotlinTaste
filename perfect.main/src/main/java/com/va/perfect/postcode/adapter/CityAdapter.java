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
import com.va.perfect.net.dao.postcode.ProvinceBean;
import com.va.perfect.postcode.PostCodeViewModel;

import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/9
 */

public class CityAdapter extends BaseRecyclerAdapter<ProvinceBean.CityBean> {

    private PostCodeViewModel postCodeViewModel;

    public void setPostCodeViewModel(PostCodeViewModel postCodeViewModel) {
        this.postCodeViewModel = postCodeViewModel;
    }

    public CityAdapter(Context mContext, List<ProvinceBean.CityBean> dataList) {
        super(mContext, dataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_city, parent, false);
        return new BaseRecyclerViewHolder(dataBinding);
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setVariable(BR.city, mDataList.get(position));
        viewDataBinding.executePendingBindings();
    }
}
