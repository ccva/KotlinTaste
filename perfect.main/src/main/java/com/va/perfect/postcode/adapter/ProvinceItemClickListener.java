package com.va.perfect.postcode.adapter;

import com.va.perfect.net.dao.postcode.ProvinceBean;

/**
 * @author Junmeng.Chen
 * @date 2017/11/9
 */

public interface ProvinceItemClickListener {

    /**
     * province 被点击
     *
     * @param provinceBean
     */
    void onProvinceClick(ProvinceBean provinceBean);

}
