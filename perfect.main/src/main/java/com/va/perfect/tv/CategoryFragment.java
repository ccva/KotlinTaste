package com.va.perfect.tv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.va.perfect.R;

import com.va.perfect.net.constant.ApiConstant;
import com.va.perfect.net.dao.tv.CategoryBean;
import com.va.perfect.net.dao.tv.ChannelBean;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.util.RxSchedulers;
import com.va.perfect.tv.adapter.CategoryAdapter;
import com.va.perfect.tv.adapter.ChannelAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author junmeng.chen
 */
public class CategoryFragment extends Fragment {

    RecyclerView lvCategory;

    RecyclerView lvChannel;

    private List<CategoryBean> mCategoryBeanList = new ArrayList<>();
    private CategoryAdapter mCategoryAdapter;

    private List<ChannelBean> mChannelBeanList = new ArrayList<>();
    private ChannelAdapter mChannelAdapter;

    public static CategoryFragment newInstance() {
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public CategoryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initView(view);
        initEvent();
        initDefault();
        return view;
    }

    private void initView(View view) {
        lvCategory = view.findViewById(R.id.lv_category);
        lvCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mCategoryAdapter = new CategoryAdapter(getContext(), mCategoryBeanList);
        lvCategory.setAdapter(mCategoryAdapter);

        mCategoryAdapter.setOnItemClickListener(position -> getChannelInfo(mCategoryBeanList.get(position).getId()));

        lvChannel = view.findViewById(R.id.lv_channel);
        lvChannel.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mChannelAdapter = new ChannelAdapter(getContext(), mChannelBeanList);
        lvChannel.setAdapter(mChannelAdapter);

        mChannelAdapter.setOnItemClickListener(position -> jumpToProgram(position));

    }

    private void jumpToProgram(int position) {
        jumpToProgramList(mChannelBeanList.get(position));
    }

    private void jumpToProgramList(ChannelBean channelBean) {
        String rel = channelBean.getRel();
        ProgramListActivity.launch(getContext(), rel, channelBean.getChannelName());
    }

    private void initDefault() {
        getCategoryInfo();
    }

    private void getCategoryInfo() {
        RetrofitService.juHeApi.getCategory(ApiConstant.TV_SIGN_KEY)
                .map(categoryDao -> categoryDao.getResult())
                .compose(RxSchedulers.io_main())
                .subscribe(categoryBeans -> notifyCategoryData(categoryBeans));
    }

    private void notifyCategoryData(List<CategoryBean> categoryBeans) {
        mCategoryBeanList.clear();
        mCategoryBeanList.addAll(categoryBeans);
        mCategoryAdapter.notifyDataSetChanged();
        getChannelInfo(mCategoryBeanList.get(0).getId());
    }

    private void getChannelInfo(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("pId", String.valueOf(id));
        params.put("key", ApiConstant.TV_SIGN_KEY);

        RetrofitService.juHeApi.getChannel(params)
                .map(channelBeanJuHeHttpResult -> channelBeanJuHeHttpResult.getResult())
                .compose(RxSchedulers.io_main())
                .subscribe(channelBeans -> notifyChannelData(channelBeans));

    }

    private void notifyChannelData(List<ChannelBean> channelBeans) {
        mChannelBeanList.clear();
        mChannelBeanList.addAll(channelBeans);
        mChannelAdapter.notifyDataSetChanged();

    }

    private void initEvent() {

    }

}
