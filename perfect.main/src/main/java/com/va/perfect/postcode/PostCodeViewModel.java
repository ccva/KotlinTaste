package com.va.perfect.postcode;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.va.perfect.Event.SingleLiveEvent;
import com.va.perfect.net.constant.ApiConstant;
import com.va.perfect.net.dao.postcode.PostCodeInfo;
import com.va.perfect.net.dao.postcode.ProvinceBean;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.util.RxSchedulers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Junmeng.Chen
 * @date 2017/11/9
 */

public class PostCodeViewModel extends ViewModel {

    public static final String TAG = PostCodeViewModel.class.getSimpleName();

    private final SingleLiveEvent<ProvinceBean> provinceClickEvent = new SingleLiveEvent<>();

    private final SingleLiveEvent<ProvinceBean.CityBean> cityClickEvent = new SingleLiveEvent<>();

    private final SingleLiveEvent<ProvinceBean.CityBean.DistrictBean> districtClickEvent = new SingleLiveEvent<>();

    private MutableLiveData<List<ProvinceBean>> provinceBeanList = null;

    private MutableLiveData<List<ProvinceBean.CityBean>> cityBeanList = null;

    private MutableLiveData<List<ProvinceBean.CityBean.DistrictBean>> districtBeanList = null;

    private MutableLiveData<ArrayList<PostCodeInfo.PostCodeBean>> postCodeBeanList = null;

    public MutableLiveData<ArrayList<PostCodeInfo.PostCodeBean>> getPostCodeBeanList() {
        if (postCodeBeanList == null) {
            postCodeBeanList = new MutableLiveData<>();
            postCodeBeanList.setValue(new ArrayList<>());
        }
        return postCodeBeanList;
    }

    public MutableLiveData<List<ProvinceBean.CityBean>> getCityBeanList() {
        if (cityBeanList == null) {
            cityBeanList = new MutableLiveData<>();
            cityBeanList.setValue(new ArrayList<>());
        }
        return cityBeanList;
    }

    public MutableLiveData<List<ProvinceBean.CityBean.DistrictBean>> getDistrictBeanList() {
        if (districtBeanList == null) {
            districtBeanList = new MutableLiveData<>();
            districtBeanList.setValue(new ArrayList<>());
        }
        return districtBeanList;
    }

    public LiveData<List<ProvinceBean>> getProvinceBeanList() {
        if (provinceBeanList == null) {
            provinceBeanList = new MutableLiveData<>();
            provinceBeanList.setValue(new ArrayList<>());
        }
        return provinceBeanList;
    }


    public SingleLiveEvent<ProvinceBean> getProvinceClickEvent() {
        return provinceClickEvent;
    }

    public SingleLiveEvent<ProvinceBean.CityBean> getCityClickEvent() {
        return cityClickEvent;
    }

    public SingleLiveEvent<ProvinceBean.CityBean.DistrictBean> getDistrictClickEvent() {
        return districtClickEvent;
    }

    public PostCodeViewModel() {

    }

    public void start() {
        getProvinceData();
    }

    private void getProvinceData() {
        RetrofitService.juHeApi.getPostCodeInfo(ApiConstant.POST_CODE_KEY)
                .map(listBaseHttpResult -> listBaseHttpResult.getResult())
                .compose(RxSchedulers.io_main())
                .subscribe(provinceBeans -> {
                    List<ProvinceBean> value = provinceBeanList.getValue();
                    value.clear();
                    value.addAll(provinceBeans);
                    provinceBeanList.setValue(value);
                });
    }

    public void getPostCodeInfo(int provinceId, int cityId, int districtId) {
        Map<String, Object> params = new HashMap<>(4);
        params.put("key", ApiConstant.POST_CODE_KEY);
        params.put("pid", provinceId);
        params.put("cid", cityId);
        if (districtId != 0) {
            params.put("did", districtId);
        }
        RetrofitService.juHeApi.searchPostCode(params)
                .map(result -> result.getResult())
                .compose(RxSchedulers.io_main())
                .subscribe(postCodeInfo -> {
                    ArrayList<PostCodeInfo.PostCodeBean> value = postCodeBeanList.getValue();
                    value.clear();
                    value.addAll(postCodeInfo.getList());
                    postCodeBeanList.setValue(value);
                }, throwable -> {
                });
    }

}
