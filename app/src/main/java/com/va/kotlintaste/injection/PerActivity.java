package com.va.kotlintaste.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Junmeng.Chen
 * @date 2017/10/19
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
