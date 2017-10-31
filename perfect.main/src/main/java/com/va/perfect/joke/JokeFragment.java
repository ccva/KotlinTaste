package com.va.perfect.joke;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.fragment.BaseListFragment;
import com.va.perfect.joke.adapter.JokeAdapter;
import com.va.perfect.net.dao.joke.JokeBean;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.net.util.RxSchedulers;


/**
 * @author cjm
 */
public class JokeFragment extends BaseListFragment<JokeBean> {

    public static JokeFragment newInstance() {

        Bundle args = new Bundle();

        JokeFragment fragment = new JokeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public JokeFragment() {

    }

    @Override
    protected boolean isNeedShowRefreshAnim() {
        return false;
    }

    @Override
    protected void setRecyclerConfig(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        return new JokeAdapter(mContext, mDataList);
    }

    @Override
    protected void refreshData() {
        RetrofitService.juHeApi.getJokeList()
                .map(listJuHeHttpResult -> listJuHeHttpResult.getResult())
                .compose(RxSchedulers.io_main())
                .subscribe(jokeBeans -> notifyDataSetChanged(),
                        throwable -> {},
                        () -> completeRefresh());
    }

    @Override
    public boolean isNeedLoadOnStart() {
        return true;
    }
}
