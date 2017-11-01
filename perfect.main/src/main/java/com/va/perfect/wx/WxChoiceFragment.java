package com.va.perfect.wx;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.fragment.BaseListFragment;
import com.va.perfect.net.constant.ApiConstant;
import com.va.perfect.net.dao.wx.WxChoiceListBean;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.util.RxSchedulers;
import com.va.perfect.wx.adapter.WxChoiceAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjm on 17-11-1.
 */

public class WxChoiceFragment extends BaseListFragment<WxChoiceListBean.WxChoiceBean> {

    private int mBeginPage = 1;

    public static WxChoiceFragment newInstance() {
        Bundle args = new Bundle();
        WxChoiceFragment fragment = new WxChoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setRecyclerConfig(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected BaseRecyclerAdapter<WxChoiceListBean.WxChoiceBean> setAdapter() {
        return new WxChoiceAdapter(getContext(), mDataList);
    }

    @Override
    public void onStart() {
        super.onStart();
        setNeedLoadOnStart(false);
    }

    @Override
    protected void refreshData() {
        Map<String, Object> params = new HashMap<>();
        params.put("key", ApiConstant.WX_SIGN_KEY);
        params.put("pno", mBeginPage);
        params.put("ps", 50);
        RetrofitService.juHeApi.getWxChoice(params).map(listJuHeHttpResult -> listJuHeHttpResult.getResult().getList()).compose(RxSchedulers.io_main()).subscribe(wxChoices -> {
            mDataList.clear();
            mDataList.addAll(wxChoices);
            notifyDataSetChanged();
            mBeginPage++;
        }, throwable -> Log.e("cjm", "refreshData: ", throwable), () -> completeRefresh());
    }

}
