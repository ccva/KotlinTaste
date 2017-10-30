package com.va.perfect.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Junmeng.Chen
 * @date 2017/10/20
 */

public class BaseActivity extends AppCompatActivity {

    public static String TAG = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getComponentName().getShortClassName();
    }
}
