package com.va.perfect.joke;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.decoration.SimpleDecoration;
import com.va.perfect.fragment.BaseListFragment;
import com.va.perfect.joke.adapter.JokeAdapter;
import com.va.perfect.net.dao.joke.JokeBean;

import java.util.List;


/**
 * @author cjm
 */
public class JokeFragment extends BaseListFragment<JokeBean> implements JokeContact.JokeView {

    public static final String TAG = JokeFragment.class.getSimpleName();

    private JokeContact.JokePresenter jokePresenter;

    public static JokeFragment newInstance() {
        Bundle args = new Bundle();
        JokeFragment fragment = new JokeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setRecyclerConfig(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

        SimpleDecoration simpleDecoration = new SimpleDecoration(mContext);
        simpleDecoration.setDividerMarginLeft(R.dimen.divider_margin_8);
        simpleDecoration.setDividerMarginRight(R.dimen.divider_margin_8);
        recyclerView.addItemDecoration(simpleDecoration);
    }

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        return new JokeAdapter(mContext, mDataList);
    }

    @Override
    protected void initCreateViewDefault(Bundle savedInstanceState) {
        jokePresenter = new JokePresenterImpl(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        setNeedLoadOnStart(false);
    }

    @Override
    protected void refreshData() {
        getJokeListData();
    }


    public void getJokeListData() {
        jokePresenter.getJokeListData();
    }

    @Override
    public void setJokeListData(List<JokeBean> jokeBeans) {
        mDataList.clear();
        mDataList.addAll(jokeBeans);
        notifyDataSetChanged();
    }

    @Override
    public void setJokeListDataError(Throwable throwable) {
        LogUtils.i(TAG, throwable.getMessage());
    }

    @Override
    public void setGetDataComplete() {
        completeRefresh();
    }
}
