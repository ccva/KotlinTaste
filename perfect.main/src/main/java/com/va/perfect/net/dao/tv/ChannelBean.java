package com.va.perfect.net.dao.tv;

import java.io.Serializable;

/**
 *
 * @author cjm
 * @date 17-10-29
 */


public class ChannelBean implements Serializable {
    /**
     * channelName : 安徽卫视
     * pId : 2
     * rel : anhui
     * url : http://tv.cntv.cn/live/anhui
     */

    private String channelName;
    private int pId;
    private String rel;
    private String url;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public int getPId() {
        return pId;
    }

    public void setPId(int pId) {
        this.pId = pId;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
