package com.va.perfect.tv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.va.perfect.R;
import com.va.perfect.net.dao.result.CategoryDao;
import com.va.perfect.net.dao.result.ChannelDao;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.net.util.RequestUtils;
import com.va.perfect.tv.adapter.CategoryAdapter;
import com.va.perfect.tv.adapter.ChannelAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {

    RecyclerView lvCategory;

    RecyclerView lvChannel;

    private List<CategoryDao.CategoryBean> mCategoryBeanList = new ArrayList<>();
    private CategoryAdapter mCategoryAdapter;

    private List<ChannelDao.ChannelBean> mChannelBeanList = new ArrayList<>();
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


    }

    private void initDefault() {
        getCategoryInfo();
    }

    private void getCategoryInfo() {
        RetrofitService.juHeApi.getCategory(RequestUtils.SIGN_KEY).subscribeOn(Schedulers.io()).map(categoryDao -> categoryDao.getResult()).observeOn(AndroidSchedulers.mainThread()).subscribe(categoryBeans -> notifyCategoryData(categoryBeans));
    }

    private void notifyCategoryData(List<CategoryDao.CategoryBean> categoryBeans) {
        mCategoryBeanList.clear();
        mCategoryBeanList.addAll(categoryBeans);
        mCategoryAdapter.notifyDataSetChanged();
        getChannelInfo(mCategoryBeanList.get(0).getId());
    }

    private void getChannelInfo(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("key", RequestUtils.SIGN_KEY);
        params.put("pId", String.valueOf(id));

        RetrofitService.juHeApi.getChannel(params)
                .subscribeOn(Schedulers.io())
                .map(channelDao -> channelDao.getResult())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(channelBeans -> notifyChannelData(channelBeans),throwable -> {});

    }

    private void notifyChannelData(List<ChannelDao.ChannelBean> channelBeans) {
        mChannelBeanList.clear();
        mChannelBeanList.addAll(channelBeans);
        mChannelAdapter.notifyDataSetChanged();

    }

    private void initEvent() {

    }

}
