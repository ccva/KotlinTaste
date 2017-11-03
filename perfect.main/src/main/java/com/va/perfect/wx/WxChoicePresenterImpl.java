package com.va.perfect.wx;

import com.va.perfect.net.dao.wx.WxChoiceListBean;

import java.lang.ref.WeakReference;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 */

public class WxChoicePresenterImpl implements WxChoiceContact.WxChoicePresenter {

    private WeakReference<WxChoiceContact.WxChoiceView> wxChoiceView;

    private WxModel wxModel;

    public WxChoicePresenterImpl(WxChoiceContact.WxChoiceView wxChoiceView) {
        this.wxChoiceView = new WeakReference<>(wxChoiceView);
        this.wxModel = new WxModel();
    }

    @Override
    public void getWxChoiceData(int pno, int ps) {
        wxModel.getWxChoiceData(pno, ps, this);
    }

    @Override
    public void setWxChoiceData(WxChoiceListBean wxChoiceListBean) {
        wxChoiceView.get().setWxChoiceData(wxChoiceListBean);
    }

    @Override
    public void setWxChoiceDataError(Throwable throwable) {
        wxChoiceView.get().setErrorData(throwable);
    }

    @Override
    public void onGetWxChoiceDataComplete() {
        wxChoiceView.get().completeGetWxChoiceData();
    }
}
