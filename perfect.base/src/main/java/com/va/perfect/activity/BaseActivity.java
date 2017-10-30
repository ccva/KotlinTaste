package com.va.perfect.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Junmeng.Chen
 * @date 2017/10/20
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static String TAG = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }


    protected void showSnack(CharSequence msg) {
        Snackbar.make(getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT).show();
    }

}
