package com.va.daggerdemo.main;

import com.va.daggerdemo.di.FuckSay;
import com.va.daggerdemo.data.Say;
import com.va.daggerdemo.di.HelloSay;

import dagger.Module;
import dagger.Provides;

/**
 * @author Junmeng.Chen
 * @date 2017/10/20
 */
@Module
public class MainModule {

    @Provides
    Say getSay(@HelloSay Say say){
        return say;
    }


}
