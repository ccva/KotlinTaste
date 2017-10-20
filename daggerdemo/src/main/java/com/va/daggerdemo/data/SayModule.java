package com.va.daggerdemo.data;

import com.va.daggerdemo.di.FuckSay;
import com.va.daggerdemo.di.HelloSay;

import dagger.Module;
import dagger.Provides;

/**
 * @author Junmeng.Chen
 * @date 2017/10/20
 */
@Module
public class SayModule {

    @Provides
    @FuckSay
    Say provideFuck(){
        return new Fuck();
    }

    @Provides
    @HelloSay
    Say provideHello(){
        return new Hello();
    }

}
