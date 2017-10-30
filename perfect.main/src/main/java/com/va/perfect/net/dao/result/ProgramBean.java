package com.va.perfect.net.dao.result;

import java.io.Serializable;

/**
 * @author Junmeng.Chen
 * @date 2017/10/30
 */

public class ProgramBean implements Serializable {


    /**
     * cName : CCTV-1 综合
     * pName : 动物世界-2017-170（45分钟）
     * pUrl :
     * time : 2017-10-30 00:33
     */

    private String cName;
    private String pName;
    private String pUrl;
    private String time;

    public String getCName() {
        return cName;
    }

    public void setCName(String cName) {
        this.cName = cName;
    }

    public String getPName() {
        return pName;
    }

    public void setPName(String pName) {
        this.pName = pName;
    }

    public String getPUrl() {
        return pUrl;
    }

    public void setPUrl(String pUrl) {
        this.pUrl = pUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
