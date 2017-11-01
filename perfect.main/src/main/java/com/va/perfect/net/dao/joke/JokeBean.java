package com.va.perfect.net.dao.joke;

import java.io.Serializable;

/**
 * @author Junmeng.Chen
 * @date 2017/10/31
 */

public class JokeBean implements Serializable {


    /**
     * content : ‍‍‍‍刚才看电视，对男票说：“老公！帮我去洗个苹果，削好皮给我吃。” 男票在打游戏，霸气的说：“不去！” 默默走到他旁边：“你敢不听我的话？” 男票头也不回的还嘴：“我又不是声控的。” 我啪的就是一巴掌：“你是触屏的！快去。” 不说了，看着他屁颠屁颠噘着嘴给我洗苹果去了。‍‍‍‍
     * hashId : D998B2E6D40354E52CEDC8CBADD229BC
     * unixtime : 1435283550
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
