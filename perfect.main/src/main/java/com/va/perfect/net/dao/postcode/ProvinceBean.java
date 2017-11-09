package com.va.perfect.net.dao.postcode;

import java.io.Serializable;
import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/9
 */

public class ProvinceBean implements Serializable {


    /**
     * id : 1
     * province : 上海市
     * city : [{"id":"32","city":"上海市","district":[{"id":"798","district":"南汇区"},{"id":"799","district":"卢湾区"},{"id":"800","district":"嘉定区"},{"id":"801","district":"奉贤区"},{"id":"802","district":"宝山区"},{"id":"803","district":"崇明县"},{"id":"804","district":"徐汇区"},{"id":"805","district":"普陀区"},{"id":"806","district":"杨浦区"},{"id":"807","district":"松江区"},{"id":"808","district":"浦东新区"},{"id":"809","district":"虹口区"},{"id":"810","district":"金山区"},{"id":"811","district":"长宁区"},{"id":"812","district":"闵行区"},{"id":"813","district":"闸北区"},{"id":"814","district":"青浦区"},{"id":"815","district":"静安区"},{"id":"816","district":"黄浦区"}]}]
     */

    private String id;
    private String province;
    private List<CityBean> city;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    public static class CityBean {
        /**
         * id : 32
         * city : 上海市
         * district : [{"id":"798","district":"南汇区"},{"id":"799","district":"卢湾区"},{"id":"800","district":"嘉定区"},{"id":"801","district":"奉贤区"},{"id":"802","district":"宝山区"},{"id":"803","district":"崇明县"},{"id":"804","district":"徐汇区"},{"id":"805","district":"普陀区"},{"id":"806","district":"杨浦区"},{"id":"807","district":"松江区"},{"id":"808","district":"浦东新区"},{"id":"809","district":"虹口区"},{"id":"810","district":"金山区"},{"id":"811","district":"长宁区"},{"id":"812","district":"闵行区"},{"id":"813","district":"闸北区"},{"id":"814","district":"青浦区"},{"id":"815","district":"静安区"},{"id":"816","district":"黄浦区"}]
         */

        private String id;
        private String city;
        private List<DistrictBean> district;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public List<DistrictBean> getDistrict() {
            return district;
        }

        public void setDistrict(List<DistrictBean> district) {
            this.district = district;
        }

        public static class DistrictBean {
            /**
             * id : 798
             * district : 南汇区
             */

            private String id;
            private String district;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDistrict() {
                return district;
            }

            public void setDistrict(String district) {
                this.district = district;
            }
        }
    }
}
