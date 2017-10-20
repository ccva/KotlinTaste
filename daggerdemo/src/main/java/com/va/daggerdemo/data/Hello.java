package com.va.daggerdemo.data;

/**
 * @author Junmeng.Chen
 * @date 2017/10/20
 */

public class Hello implements Say {
    @Override
    public String saySomething() {
        return "hello the world";
    }
}
