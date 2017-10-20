package com.va.daggerdemo.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.va.daggerdemo.R;
import com.va.daggerdemo.data.Say;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Say say;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = findViewById(R.id.tv);
        DaggerMainComponent.create().inject(this);
        tv.setText(say.saySomething());
    }
}
