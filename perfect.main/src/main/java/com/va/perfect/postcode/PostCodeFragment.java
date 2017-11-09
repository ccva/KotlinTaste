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
import com.va.perfect.postcode.adapter.CityAdapter;
import com.va.perfect.postcode.adapter.ProvinceAdapter;

/**
 * @author Junmeng.Chen
 * @date 2017/11/9
 */

public class PostCodeFragment extends BaseFragment {

    private RecyclerView rVProvince;

    private RecyclerView rVCity;

    private RecyclerView rVDistrict;
    private PostCodeViewModel postCodeViewModel;
    private ProvinceAdapter provinceAdapter;

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
    }

    private void initView() {
        rVProvince = mRootView.findViewById(R.id.recycler_view_province);
        rVProvince.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        provinceAdapter = new ProvinceAdapter(mContext, postCodeViewModel.getProvinceBeanList().getValue());
        provinceAdapter.setPostCodeViewModel(postCodeViewModel);
        rVProvince.setAdapter(provinceAdapter);

        rVCity = mRootView.findViewById(R.id.recycler_view_city);
        rVCity.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        rVCity.setAdapter(new CityAdapter(mContext, postCodeViewModel.getCityBeanList().getValue()));

        rVDistrict = mRootView.findViewById(R.id.recycler_view_district);
    }

    private void initEvent() {
        postCodeViewModel = obtainViewModel(getActivity());

        postCodeViewModel.getProvinceBeanList().observe(this, districtInfoList -> {
            provinceAdapter.notifyDataSetChanged();
        });
    }

    private static PostCodeViewModel obtainViewModel(FragmentActivity fragmentActivity) {
        PostCodeViewModel postCodeViewModel = ViewModelProviders.of(fragmentActivity).get(PostCodeViewModel.class);
        return postCodeViewModel;
    }


}
