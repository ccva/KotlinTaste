package com.va.daggerdemo.model;

import android.os.Looper;

import com.va.daggerdemo.view.IMainView;

import javax.inject.Inject;


public class MainModel {

    private long lastClickTime = 0;

    @Inject
    public MainModel() {
    }

    public String getSyncMessage(IMainView mainView) {
        if (lastClickTime == 0) {
            lastClickTime = System.currentTimeMillis();
        } else {
            boolean isDoubleCheck = false;
            if (System.currentTimeMillis() - lastClickTime < 1000) {
                isDoubleCheck = true;
            }
            lastClickTime = System.currentTimeMillis();
            if (isDoubleCheck) {
                String waringString = "don't double click in short time";
                mainView.showErrorMessage(waringString);
                return null;
            }
        }
        String s = "hello from sync";
        mainView.showMessage(s);
        return s;
    }

    public void getAsyncMessage(IMainView mainView) {
        new android.os.Handler(Looper.getMainLooper()).post(() -> mainView.showMessage("hello from Async"));
    }

}
