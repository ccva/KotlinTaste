package com.va.perfect.postcode.adapter;

import com.va.perfect.net.dao.postcode.ProvinceBean;

/**
 * @author Junmeng.Chen
 * @date 2017/11/9
 */

public interface PostCodeClickListener {

    interface ProvinceClickListener {
        /**
         * province 被点击
         *
         * @param provinceBean
         */
        void onProvinceClick(ProvinceBean provinceBean);
    }

    interface CityClickListener {
        /**
         * 城市点击
         *
         * @param cityBean
         */
        void onCityClick(ProvinceBean.CityBean cityBean);
    }

    interface DistractClickListener {
        /**
         * 区域被点击
         *
         * @param districtBean
         */
        void onDistractClick(ProvinceBean.CityBean.DistrictBean districtBean);
    }

}
