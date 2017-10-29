package com.va.perfect.main;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.activity.BaseActivity;
import com.va.perfect.net.dao.result.CategoryDao;
import com.va.perfect.net.request.JuHeRequest;
import com.va.perfect.tv.CategoryFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    FrameLayout flContainer;

    ListView lvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();

        initDefault();
    }

    private void initView() {
        flContainer = findViewById(R.id.fl_container);

        lvMenu = findViewById(R.id.lv_menu);
    }

    private void initEvent() {
        lvMenu.setOnItemClickListener((parent, view, position, id) -> {

        });
    }

    private void initDefault() {
        CategoryFragment categoryFragment = CategoryFragment.newInstance();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_container,categoryFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
