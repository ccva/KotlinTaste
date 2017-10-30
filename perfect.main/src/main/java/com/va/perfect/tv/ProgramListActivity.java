package com.va.perfect.tv;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.va.perfect.activity.BaseListActivity;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.net.dao.result.ProgramBean;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.net.util.RxSchedulers;
import com.va.perfect.tv.adapter.ProgramAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cjm
 */
public class ProgramListActivity extends BaseListActivity<ProgramBean> {

    public static final String CODE = "code";

    public static final String CHANNEL = "channel";

    private String mCode;

    private String mChannel;

    public static void launch(Context context, String code, String channel) {
        Intent intent = new Intent(context, ProgramListActivity.class);
        intent.putExtra(CODE, code);
        intent.putExtra(CHANNEL, channel);
        context.startActivity(intent);
    }

    @Override
    protected void getResult(Intent intent) {
        mCode = intent.getStringExtra(CODE);
        mChannel = intent.getStringExtra(CHANNEL);
    }

    @Override
    protected void setRecyclerConfig(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        return new ProgramAdapter(getBaseContext(), mDataList);
    }

    @Override
    public void onItemClick(int position) {
        String pName = mDataList.get(position).getPName();
        showSnack(pName);
    }

    @Override
    protected void initDefault() {
        setTitle(mChannel);
        getProgramInfo(mCode, "");
    }

    /**
     * @param code
     * @param date 格式yyyy-MM-dd,默认为当天日期
     */
    private void getProgramInfo(String code, String date) {
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        RetrofitService.juHeApi.getProgram(params)
                .map(listJuHeHttpResult -> listJuHeHttpResult.getResult())
                .compose(RxSchedulers.io_main())
                .subscribe(programBeans -> notifyProgramBean(programBeans), throwable -> {
                    Log.e(TAG, "getProgramInfo: ", throwable);
                });
    }

    private void notifyProgramBean(List<ProgramBean> programBeans) {
        mDataList.clear();
        mDataList.addAll(programBeans);
        notifyDataSetChanged();
    }
}
