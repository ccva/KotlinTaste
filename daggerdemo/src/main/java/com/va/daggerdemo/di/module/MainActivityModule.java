package com.va.daggerdemo.di.module;

import com.va.daggerdemo.view.IMainView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private IMainView mainView;

    public MainActivityModule(IMainView mainView) {
        this.mainView = mainView;
    }

    @Provides
    public IMainView provideMainView(){
        return mainView;
    }
}
