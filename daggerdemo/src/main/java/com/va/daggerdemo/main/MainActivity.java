package com.va.daggerdemo.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.va.daggerdemo.R;
import com.va.daggerdemo.di.compinent.DaggerMainActivityComponent;
import com.va.daggerdemo.di.module.MainActivityModule;
import com.va.daggerdemo.presenter.MainPresenter;
import com.va.daggerdemo.view.IMainView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements IMainView {

    private TextView tvShow;
    private Button btnAsync, btnSync;

    @Inject
    public MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainActivityComponent
                .builder()
                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);

        tvShow = findViewById(R.id.tv);
        btnAsync = findViewById(R.id.btn_async);
        btnSync = findViewById(R.id.btn_sync);

        btnAsync.setOnClickListener(v -> mainPresenter.getAsyncDataMessage());
        btnSync.setOnClickListener(v -> mainPresenter.getSyncDataMessage());

    }

    @Override
    public void showMessage(String s) {
        if (tvShow != null) {
            tvShow.setText(s);
        }
    }

    @Override
    public void showErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
