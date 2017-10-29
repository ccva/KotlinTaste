package com.va.perfect.net.dao.result;

import java.util.List;

/**
 * Created by cjm on 17-10-29.
 */

public class CategoryDao {


    /**
     * error_code : 0
     * reason : 请求成功！
     * result : [{"id":1,"name":"央视"},{"id":2,"name":"卫视"},{"id":3,"name":"数字"},{"id":4,"name":"城市"},{"id":5,"name":"CETV"},{"id":6,"name":"原创"}]
     */

    private int error_code;
    private String reason;
    private List<CategoryBean> result;

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

    public List<CategoryBean> getResult() {
        return result;
    }

    public void setResult(List<CategoryBean> result) {
        this.result = result;
    }

    public static class CategoryBean {
        /**
         * id : 1
         * name : 央视
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
