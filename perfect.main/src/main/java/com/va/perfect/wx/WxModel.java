package com.va.perfect.wx;

import com.va.perfect.model.BaseModel;
import com.va.perfect.net.constant.ApiConstant;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.util.RxSchedulers;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 * <p>
 * <p>
 * 只是用来获取数据 不关心 数据给谁 怎么显示
 */

public class WxModel extends BaseModel {

    public void getWxChoiceData(int pno, int ps, WxChoiceContact.WxChoicePresenter wxChoicePresenter) {
        Map<String, Object> params = new HashMap<>(5);
        params.put("key", ApiConstant.WX_SIGN_KEY);
        params.put("pno", pno);
        params.put("ps", ps);
        RetrofitService.juHeApi
                .getWxChoice(params)
                .map(new HandleResult<>())
                .compose(RxSchedulers.io_main())
                .onErrorResumeNext(new HttpResponseFunc<>())
                .subscribe(
                        wxChoicePresenter::setWxChoiceData,
                        wxChoicePresenter::setWxChoiceDataError,
                        wxChoicePresenter::onGetWxChoiceDataComplete);

    }

}
