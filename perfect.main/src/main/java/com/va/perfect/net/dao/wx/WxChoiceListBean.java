package com.va.perfect.net.dao.wx;

import java.util.List;

/**
 *
 * @author cjm
 * @date 17-11-1
 */

public class WxChoiceListBean {
    /**
     * totalPage : 3760
     * ps : 50
     * pno : 1
     */

    private int totalPage;
    private int ps;
    private int pno;
    private List<WxChoiceBean> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPs() {
        return ps;
    }

    public void setPs(int ps) {
        this.ps = ps;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public List<WxChoiceBean> getList() {
        return list;
    }

    public void setList(List<WxChoiceBean> list) {
        this.list = list;
    }

    public static class WxChoiceBean {
        /**
         * id : wechat_20171101047824
         * title : 【车友互动】日系控们请往这里看，两种尾灯大家喜欢哪一种？
         * source : 改装车
         * firstImg : http://zxpic.gtimg.com/infonew/0/wechat_pics_-56818989.jpg/640
         * mark :
         * url : http://v.juhe.cn/weixin/redirect?wid=wechat_20171101047824
         */

        private String id;
        private String title;
        private String source;
        private String firstImg;
        private String mark;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getFirstImg() {
            return firstImg;
        }

        public void setFirstImg(String firstImg) {
            this.firstImg = firstImg;
        }

        public String getMark() {
            return mark;
        }

        public void setMark(String mark) {
            this.mark = mark;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
