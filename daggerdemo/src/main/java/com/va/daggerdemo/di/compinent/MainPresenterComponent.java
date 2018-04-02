package com.va.daggerdemo.di.compinent;

import com.va.daggerdemo.presenter.MainPresenter;

import dagger.Component;

@Component
public interface MainPresenterComponent {

    void inject(MainPresenter mainPresenter);

}
