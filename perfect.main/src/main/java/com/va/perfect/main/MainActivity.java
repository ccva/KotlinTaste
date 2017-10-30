package com.va.perfect.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.va.perfect.R;
import com.va.perfect.activity.BaseActivity;
import com.va.perfect.tv.CategoryFragment;

/**
 * @author cjm
 */
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
        fragmentTransaction.add(R.id.fl_container, categoryFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

}
