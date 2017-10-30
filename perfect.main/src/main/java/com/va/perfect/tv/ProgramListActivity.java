package com.va.perfect.tv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.va.perfect.R;
import com.va.perfect.activity.BaseActivity;
import com.va.perfect.net.dao.result.ProgramBean;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.net.util.RxSchedulers;
import com.va.perfect.tv.adapter.ProgramAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cjm
 */
public class ProgramListActivity extends BaseActivity {

    public static final String CODE = "code";

    private RecyclerView recyclerView;

    private List<ProgramBean> programBeanList = new ArrayList<>();

    private ProgramAdapter programAdapter;

    private String mCode;

    public static void launch(Context context, String code) {
        Intent intent = new Intent(context, ProgramListActivity.class);
        intent.putExtra(CODE, code);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);

        getResult(getIntent());

        recyclerView = findViewById(R.id.recycler_program_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        programAdapter = new ProgramAdapter(getBaseContext(), programBeanList);
        recyclerView.setAdapter(programAdapter);

        initDefault();
    }

    private void getResult(Intent intent) {
        mCode = intent.getStringExtra(CODE);
    }

    private void initDefault() {
        getProgramInfo(mCode, "");
    }

    /**
     *
     * @param code
     * @param date  格式yyyy-MM-dd,默认为当天日期
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
        programBeanList.clear();
        programBeanList.addAll(programBeans);
        programAdapter.notifyDataSetChanged();
    }
}
