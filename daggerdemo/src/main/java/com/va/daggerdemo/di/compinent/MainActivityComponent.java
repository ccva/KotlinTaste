package com.va.daggerdemo.di.compinent;

import com.va.daggerdemo.di.module.MainActivityModule;
import com.va.daggerdemo.main.MainActivity;

import dagger.Component;

@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

}
