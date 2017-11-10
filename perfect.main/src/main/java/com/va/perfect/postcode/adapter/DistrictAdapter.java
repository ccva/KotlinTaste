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
 * @date 2017/11/10
 */

public class DistrictAdapter extends BaseRecyclerAdapter<ProvinceBean.CityBean.DistrictBean> {

    PostCodeViewModel postCodeViewModel;

    public void setPostCodeViewModel(PostCodeViewModel postCodeViewModel) {
        this.postCodeViewModel = postCodeViewModel;
    }

    public DistrictAdapter(Context mContext, List<ProvinceBean.CityBean.DistrictBean> dataList) {
        super(mContext, dataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding dataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_district_post_code, parent, false);
        return new BaseRecyclerViewHolder(dataBinding);
    }

    @Override
    protected void onBindView(BaseRecyclerViewHolder holder, int position) {
        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        ProvinceBean.CityBean.DistrictBean districtBean = mDataList.get(position);
        viewDataBinding.setVariable(BR.district, districtBean);
        PostCodeClickListener.DistractClickListener listener = district -> postCodeViewModel.getDistrictClickEvent().setValue(district);
        viewDataBinding.setVariable(BR.listener, listener);
    }
}
