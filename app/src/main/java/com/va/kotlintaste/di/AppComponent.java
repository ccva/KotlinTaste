package com.va.kotlintaste.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Junmeng.Chen
 * @date 2017/10/18
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    Application getApplication();

}
