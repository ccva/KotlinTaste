package com.va.daggerdemo.presenter;

import com.va.daggerdemo.di.compinent.DaggerMainPresenterComponent;
import com.va.daggerdemo.view.IMainView;
import com.va.daggerdemo.model.MainModel;

import javax.inject.Inject;

public class MainPresenter {


    private IMainView mainView;

    @Inject
    public MainModel mainModel;

    @Inject
    public MainPresenter(IMainView mainView) {
        this.mainView = mainView;
        DaggerMainPresenterComponent.builder().build().inject(this);
    }


    public void getAsyncDataMessage() {
        mainModel.getAsyncMessage(mainView);
    }

    public void getSyncDataMessage() {
        mainModel.getSyncMessage(mainView);
    }
}
