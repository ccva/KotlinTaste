package com.va.perfect.net.dao.joke;

import java.io.Serializable;

/**
 * @author Junmeng.Chen
 * @date 2017/10/31
 */

public class JokeBean implements Serializable {


    /**
     * content : 二月，是一年中最短的一个月，是一年中工资最多的一个月，是一年中情人最多的一个月，是一年中约会最多的一个月，是一年中送礼最多的一个月，是一年中最热闹的一个月，是一年中最浪漫的一个月，是一年中最温馨的一个月......但也是一年中-----资金最短缺的一个月！
     * hashId : 6CF275601E75DEAE314FC5875C3094B2
     * unixtime : 1423936329
     */

    private String content;
    private String hashId;
    private String unixtime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashId() {
        return hashId;
    }

    public void setHashId(String hashId) {
        this.hashId = hashId;
    }

    public String getUnixtime() {
        return unixtime;
    }

    public void setUnixtime(String unixtime) {
        this.unixtime = unixtime;
    }
}
