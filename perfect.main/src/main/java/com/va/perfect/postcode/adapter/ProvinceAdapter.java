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

public class ProvinceAdapter extends BaseRecyclerAdapter<ProvinceBean> {

    private PostCodeViewModel postCodeViewModel;

    public void setPostCodeViewModel(PostCodeViewModel postCodeViewModel) {
        this.postCodeViewModel = postCodeViewModel;
    }

    public ProvinceAdapter(Context mContext, List<ProvinceBean> dataList) {
        super(mContext, dataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_province, parent, false);
        return new BaseRecyclerViewHolder(binding);
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setVariable(BR.province, mDataList.get(position));
        ProvinceItemClickListener provinceItemClickListener = provinceBean -> {
            postCodeViewModel.getProvinceClickEvent().setValue(provinceBean);
        };
        viewDataBinding.setVariable(BR.listener, provinceItemClickListener);
        viewDataBinding.executePendingBindings();
    }
}
