package com.va.kotlintaste.di;

import android.app.Application;
import android.content.Context;

import com.va.kotlintaste.config.GlobalConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Junmeng.Chen
 * @date 2017/10/18
 */
@Singleton
@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    GlobalConfig provideGlobalConfig() {
        return new GlobalConfig();
    }

}
