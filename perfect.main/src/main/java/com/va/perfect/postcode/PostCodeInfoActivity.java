package com.va.perfect.postcode;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.va.perfect.activity.BaseListActivity;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.net.dao.postcode.PostCodeInfo;
import com.va.perfect.postcode.adapter.PostCodeInfoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/10
 */

public class PostCodeInfoActivity extends BaseListActivity<PostCodeInfo.PostCodeBean> {

    public static final String EXTRA_DATA_LIST = "extra_data_list";

    public static void launch(Context context, ArrayList<PostCodeInfo.PostCodeBean> postCodeInfoList) {
        Intent intent = new Intent(context, PostCodeInfoActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_DATA_LIST, postCodeInfoList);
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedShowRefreshAnim() {
        return false;
    }

    @Override
    public boolean setCanRefresh() {
        return false;
    }

    @Override
    protected void getResult(Intent intent) {
        List<PostCodeInfo.PostCodeBean> postCodeBeanList = intent.getParcelableArrayListExtra(EXTRA_DATA_LIST);
        mDataList.clear();
        mDataList.addAll(postCodeBeanList);
    }

    @Override
    protected void setRecyclerConfig(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        return new PostCodeInfoAdapter(getBaseContext(), mDataList);
    }

}
