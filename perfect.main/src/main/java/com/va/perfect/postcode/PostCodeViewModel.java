package com.va.perfect.postcode;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.va.perfect.Event.SingleLiveEvent;
import com.va.perfect.net.constant.ApiConstant;
import com.va.perfect.net.dao.postcode.ProvinceBean;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.util.RxSchedulers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/9
 */

public class PostCodeViewModel extends ViewModel {

    private final SingleLiveEvent<ProvinceBean> provinceClickEvent = new SingleLiveEvent<>();

    private final SingleLiveEvent<ProvinceBean.CityBean> cityClickEvent = new SingleLiveEvent<>();

    private final SingleLiveEvent<ProvinceBean.CityBean.DistrictBean> districtClickEvent = new SingleLiveEvent<>();

    private List<ProvinceBean> provinceBeans = new ArrayList<>();

    MutableLiveData mutableLiveData = new MutableLiveData<List<ProvinceBean>>();

    private LiveData<List<ProvinceBean>> provinceBeanList = mutableLiveData;

    private LiveData<List<ProvinceBean.CityBean>> cityBeanList = new MutableLiveData<>();

    private LiveData<List<ProvinceBean.CityBean.DistrictBean>> districtBeanList = new MutableLiveData<>();

    public LiveData<List<ProvinceBean.CityBean>> getCityBeanList() {
        return cityBeanList;
    }

    public LiveData<List<ProvinceBean.CityBean.DistrictBean>> getDistrictBeanList() {
        return districtBeanList;
    }

    public LiveData<List<ProvinceBean>> getProvinceBeanList() {
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
        mutableLiveData.postValue(provinceBeans);
    }

    public void start() {
        getData();
    }

    private void getData() {
        RetrofitService.juHeApi.getPostCodeInfo(ApiConstant.POST_CODE_KEY)
                .map(listBaseHttpResult -> listBaseHttpResult.getResult())
                .compose(RxSchedulers.io_main())
                .subscribe(provinceBeans -> {
                    List<ProvinceBean> value = provinceBeanList.getValue();
                    value.clear();
                    value.addAll(provinceBeans);
                });
    }

}
