package com.va.perfect.postcode;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.va.perfect.R;
import com.va.perfect.fragment.BaseFragment;
import com.va.perfect.net.dao.postcode.ProvinceBean;
import com.va.perfect.postcode.adapter.CityAdapter;
import com.va.perfect.postcode.adapter.DistrictAdapter;
import com.va.perfect.postcode.adapter.ProvinceAdapter;

import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/10
 */
public class PostCodeFragment extends BaseFragment {

    /**
     * The constant TAG.
     */
    public static final String TAG = PostCodeFragment.class.getSimpleName();

    private RecyclerView rVProvince;
    private ProvinceAdapter provinceAdapter;

    private RecyclerView rVCity;
    private CityAdapter cityAdapter;

    private RecyclerView rVDistrict;
    private DistrictAdapter districtAdapter;

    private PostCodeViewModel postCodeViewModel;

    private String mProvinceId = "0";
    private String mCityId = "0";
    private String mDistrictId = "0";

    /**
     * New instance post code fragment.
     *
     * @return the post code fragment
     */
    public static PostCodeFragment newInstance() {

        Bundle args = new Bundle();

        PostCodeFragment fragment = new PostCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View inflaterRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post_code, container, false);
    }

    @Override
    protected void initCreateViewDefault(Bundle savedInstanceState) {

        initEvent();

        initView();

    }

    @Override
    public void onResume() {
        super.onResume();
        postCodeViewModel.start();
        setNeedLoadOnStart(false);
    }

    private void initView() {
        rVProvince = mRootView.findViewById(R.id.recycler_view_province);
        rVProvince.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        provinceAdapter = new ProvinceAdapter(mContext, postCodeViewModel.getProvinceBeanList().getValue());
        provinceAdapter.setPostCodeViewModel(postCodeViewModel);
        rVProvince.setAdapter(provinceAdapter);

        rVCity = mRootView.findViewById(R.id.recycler_view_city);
        rVCity.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        cityAdapter = new CityAdapter(mContext, postCodeViewModel.getCityBeanList().getValue());
        cityAdapter.setPostCodeViewModel(postCodeViewModel);
        rVCity.setAdapter(cityAdapter);

        rVDistrict = mRootView.findViewById(R.id.recycler_view_district);
        rVDistrict.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        districtAdapter = new DistrictAdapter(mContext, postCodeViewModel.getDistrictBeanList().getValue());
        districtAdapter.setPostCodeViewModel(postCodeViewModel);
        rVDistrict.setAdapter(districtAdapter);
    }

    private void initEvent() {
        postCodeViewModel = obtainViewModel(getActivity());

        postCodeViewModel.getProvinceBeanList().observe(this, provinceBeanList -> provinceAdapter
                .notifyDataSetChanged());

        postCodeViewModel.getCityBeanList().observe(this, cityBeans -> {
            if (cityBeans == null || cityBeans.size() == 0) {
                rVCity.setVisibility(View.GONE);
            } else {
                rVCity.setVisibility(View.VISIBLE);
                cityAdapter.notifyDataSetChanged();
            }
        });

        postCodeViewModel.getDistrictBeanList().observe(this, districtBeans -> {
            if (districtBeans == null || districtBeans.size() == 0) {
                rVDistrict.setVisibility(View.GONE);
            } else {
                rVDistrict.setVisibility(View.VISIBLE);
                districtAdapter.notifyDataSetChanged();
            }
        });

        postCodeViewModel.getPostCodeBeanList().observe(this, postCodeBeans -> {

            if (postCodeBeans == null || postCodeBeans.size() == 0) {
                return;
            }

            PostCodeInfoActivity.launch(mContext, postCodeBeans);
        });

        postCodeViewModel.getProvinceClickEvent().observe(this, provinceBean -> {
            mProvinceId = provinceBean.getId();
            rVDistrict.setVisibility(View.GONE);
            List<ProvinceBean.CityBean> cityBeanList = postCodeViewModel.getCityBeanList().getValue();
            cityBeanList.clear();
            cityBeanList.addAll(provinceBean.getCity());
            postCodeViewModel.getCityBeanList().setValue(cityBeanList);
        });

        postCodeViewModel.getCityClickEvent().observe(this, cityBean -> {
            mCityId = cityBean.getId();
            List<ProvinceBean.CityBean.DistrictBean> districtBeanList = postCodeViewModel.getDistrictBeanList()
                    .getValue();
            List<ProvinceBean.CityBean.DistrictBean> districtBeans = cityBean.getDistrict();
            if (districtBeans == null || districtBeans.size() == 0) {
                getPostCodeInfo();
                return;
            }
            districtBeanList.clear();
            districtBeanList.addAll(districtBeans);
            postCodeViewModel.getDistrictBeanList().setValue(districtBeanList);
        });

        postCodeViewModel.getDistrictClickEvent().observe(this, districtBean -> {
            mDistrictId = districtBean.getId();
            getPostCodeInfo();
        });

    }

    private void getPostCodeInfo() {
        Integer provinceId = null;
        Integer cityId = null;
        Integer districtId = null;
        try {
            provinceId = Integer.valueOf(mProvinceId);
            cityId = Integer.valueOf(mCityId);
            districtId = Integer.valueOf(mDistrictId);
            postCodeViewModel.getPostCodeInfo(provinceId, cityId, districtId);
        } catch (NumberFormatException e) {

        }
    }

    private static PostCodeViewModel obtainViewModel(FragmentActivity fragmentActivity) {
        PostCodeViewModel postCodeViewModel = ViewModelProviders.of(fragmentActivity).get(PostCodeViewModel.class);
        return postCodeViewModel;
    }


}
