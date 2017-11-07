package com.va.perfect.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.va.perfect.R;
import com.va.perfect.activity.BaseActivity;
import com.va.perfect.chart.ChartFragment;
import com.va.perfect.joke.JokeFragment;
import com.va.perfect.test.TestFragment;
import com.va.perfect.tv.CategoryFragment;
import com.va.perfect.wx.WxChoiceFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cjm
 */
public class MainActivity extends BaseActivity {

    private String[] mMenuItems = { "Joke", "WxChoice","Tv","Chart","Test"};

    DrawerLayout drawerLayout;

    FrameLayout flContainer;

    RecyclerView recyclerView;

    private MainMenuAdapter mMenuAdapter;

    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initFragmentList();

        initEvent();

        initDefault();
    }

    private void initView() {
        drawerLayout = findViewById(R.id.drawer_layout);

        flContainer = findViewById(R.id.fl_container);

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));

        mMenuAdapter = new MainMenuAdapter(getBaseContext(), Arrays.asList(mMenuItems));

        recyclerView.setAdapter(mMenuAdapter);
    }

    private void initFragmentList() {
        fragmentList.clear();
        fragmentList.add(JokeFragment.newInstance());
        fragmentList.add(WxChoiceFragment.newInstance());
        fragmentList.add(CategoryFragment.newInstance());
        fragmentList.add(ChartFragment.newInstance());
        fragmentList.add(TestFragment.newInstance());
    }

    private void initEvent() {
        mMenuAdapter.setOnItemClickListener(position -> {
            changeFragment(position);
            drawerLayout.closeDrawers();
            setTitle(mMenuItems[position]);
        });
    }

    private void changeFragment(int position) {
        Fragment fragment = fragmentList.get(position);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        for (Fragment fragment1 : fragmentList) {
            if (!fragment1.isHidden()) {
                fragmentTransaction.hide(fragment1);
            }
        }

        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fl_container, fragment).show(fragment);
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void initDefault() {

        recyclerView.post(() -> recyclerView.getChildAt(0).performClick());
    }

}
