package com.va.perfect.wx;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.fragment.BaseListFragment;
import com.va.perfect.net.dao.wx.WxChoiceListBean;
import com.va.perfect.wx.adapter.WxChoiceAdapter;

import java.util.List;

/**
 * @author cjm
 * @date 17-11-1
 */

public class WxChoiceFragment extends BaseListFragment<WxChoiceListBean.WxChoiceBean> implements WxChoiceContact.WxChoiceView {

    private int mBeginPage = 1;

    private WxChoiceContact.WxChoicePresenter wxChoicePresenter;

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
    protected void initCreateViewDefault() {
        wxChoicePresenter = new WxChoicePresenterImpl(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setNeedLoadOnStart(false);
    }

    @Override
    protected void refreshData() {
        getWxChoiceData(mBeginPage, 50);
    }

    private void getWxChoiceData(int pno, int ps) {
        wxChoicePresenter.getWxChoiceData(pno, ps);
    }

    @Override
    public void setWxChoiceData(WxChoiceListBean wxChoiceData) {
        if (wxChoiceData == null) {
            return;
        }
        mDataList.clear();
        List<WxChoiceListBean.WxChoiceBean> wxChoices = wxChoiceData.getList();
        mDataList.addAll(wxChoices);
        notifyDataSetChanged();
        mBeginPage++;
    }

    @Override
    public void setErrorData(Throwable throwable) {
        Log.e("cjm", "refreshData: ", throwable);
    }

    @Override
    public void completeGetWxChoiceData() {
        completeRefresh();
    }

}
