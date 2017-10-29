package com.va.perfect.net.dao.result;

import java.util.List;

/**
 * Created by cjm on 17-10-29.
 */

public class ChannelDao {


    /**
     * error_code : 0
     * reason : Success!
     * result : [{"channelName":"安徽卫视","pId":2,"rel":"anhui","url":"http://tv.cntv.cn/live/anhui"},{"channelName":"北京卫视","pId":2,"rel":"btv1","url":"http://tv.cntv.cn/live/btv1"},{"channelName":"兵团卫视","pId":2,"rel":"bingtuan","url":"http://tv.cntv.cn/live/bingtuan"},{"channelName":"重庆卫视","pId":2,"rel":"chongqing","url":"http://tv.cntv.cn/live/chongqing"},{"channelName":"东方卫视","pId":2,"rel":"dongfang","url":"http://tv.cntv.cn/live/dongfang"},{"channelName":"东南卫视","pId":2,"rel":"dongnan","url":"http://tv.cntv.cn/live/dongnan"},{"channelName":"广东卫视","pId":2,"rel":"guangdong","url":"http://tv.cntv.cn/live/guangdong"},{"channelName":"广西卫视","pId":2,"rel":"guangxi","url":"http://tv.cntv.cn/live/guangxi"},{"channelName":"甘肃卫视","pId":2,"rel":"gansu","url":"http://tv.cntv.cn/live/gansu"},{"channelName":"贵州卫视","pId":2,"rel":"guizhou","url":"http://tv.cntv.cn/live/guizhou"},{"channelName":"河北卫视","pId":2,"rel":"hebei","url":"http://tv.cntv.cn/live/hebei"},{"channelName":"河南卫视","pId":2,"rel":"henan","url":"http://tv.cntv.cn/live/henan"},{"channelName":"黑龙江卫视","pId":2,"rel":"heilongjiang","url":"http://tv.cntv.cn/live/heilongjiang"},{"channelName":"湖北卫视","pId":2,"rel":"hubei","url":"http://tv.cntv.cn/live/hubei"},{"channelName":"湖南卫视","pId":2,"rel":"hunan","url":"http://tv.cntv.cn/live/hunan"},{"channelName":"吉林卫视","pId":2,"rel":"jilin","url":"http://tv.cntv.cn/live/jilin"},{"channelName":"江苏卫视","pId":2,"rel":"jiangsu","url":"http://tv.cntv.cn/live/jiangsu"},{"channelName":"江西卫视","pId":2,"rel":"jiangxi","url":"http://tv.cntv.cn/live/jiangxi"},{"channelName":"辽宁卫视","pId":2,"rel":"liaoning","url":"http://tv.cntv.cn/live/liaoning"},{"channelName":"旅游卫视","pId":2,"rel":"travel","url":"http://tv.cntv.cn/live/travel"},{"channelName":"内蒙古卫视","pId":2,"rel":"neimenggu","url":"http://tv.cntv.cn/live/neimenggu"},{"channelName":"宁夏卫视","pId":2,"rel":"ningxia","url":"http://tv.cntv.cn/live/ningxia"},{"channelName":"青海卫视","pId":2,"rel":"qinghai","url":"http://tv.cntv.cn/live/qinghai"},{"channelName":"山东卫视","pId":2,"rel":"shandong","url":"http://tv.cntv.cn/live/shandong"},{"channelName":"山东教育台","pId":2,"rel":"sdetv","url":"http://tv.cntv.cn/live/sdetv"},{"channelName":"深圳卫视","pId":2,"rel":"shenzhen","url":"http://tv.cntv.cn/live/shenzhen"},{"channelName":"陕西卫视","pId":2,"rel":"shan3xi","url":"http://tv.cntv.cn/live/shan3xi"},{"channelName":"山西卫视","pId":2,"rel":"shan1xi","url":"http://tv.cntv.cn/live/shan1xi"},{"channelName":"四川卫视","pId":2,"rel":"sichuan","url":"http://tv.cntv.cn/live/sichuan"},{"channelName":"天津卫视","pId":2,"rel":"tianjin","url":"http://tv.cntv.cn/live/tianjin"},{"channelName":"西藏卫视","pId":2,"rel":"xizang","url":"http://tv.cntv.cn/live/xizang"},{"channelName":"厦门卫视","pId":2,"rel":"xiamen","url":"http://tv.cntv.cn/live/xiamen"},{"channelName":"新疆卫视","pId":2,"rel":"xinjiang","url":"http://tv.cntv.cn/live/xinjiang"},{"channelName":"香港卫视","pId":2,"rel":"xianggangweishi","url":"http://tv.cntv.cn/live/xianggangweishi"},{"channelName":"延边卫视","pId":2,"rel":"yanbian","url":"http://tv.cntv.cn/live/yanbian"},{"channelName":"云南卫视","pId":2,"rel":"yunnan","url":"http://tv.cntv.cn/live/yunnan"},{"channelName":"浙江卫视","pId":2,"rel":"zhejiang","url":"http://tv.cntv.cn/live/zhejiang"}]
     */

    private int error_code;
    private String reason;
    private List<ChannelBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ChannelBean> getResult() {
        return result;
    }

    public void setResult(List<ChannelBean> result) {
        this.result = result;
    }

    public static class ChannelBean {
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
}
