package com.va.daggerdemo.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author Junmeng.Chen
 * @date 2017/10/20
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface FuckSay {
}
