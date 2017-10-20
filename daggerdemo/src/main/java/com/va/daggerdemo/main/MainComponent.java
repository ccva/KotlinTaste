package com.va.daggerdemo.main;

import com.va.daggerdemo.data.SayModule;

import dagger.Component;

/**
 * @author Junmeng.Chen
 * @date 2017/10/20
 */
@Component(modules = {SayModule.class,MainModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
