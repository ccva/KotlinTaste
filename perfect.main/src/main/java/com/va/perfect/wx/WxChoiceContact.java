package com.va.perfect.wx;

import com.va.perfect.net.dao.wx.WxChoiceListBean;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 */

public interface WxChoiceContact {

    interface WxChoicePresenter {

        /**
         * 获取 微信精选数据
         *
         * @param pno
         * @param ps
         */
        void getWxChoiceData(int pno, int ps);

        /**
         * 用于从 model 接收 数据
         *
         * @param wxChoiceListBean
         */
        void setWxChoiceData(WxChoiceListBean wxChoiceListBean);

        /**
         * 用于从 model 接收异常数据
         *
         * @param throwable
         */
        void setWxChoiceDataError(Throwable throwable);

        /**
         * 用于 接收 从 modeL 发送的结束信号
         */
        void onGetWxChoiceDataComplete();

    }

    interface WxChoiceView {

        /**
         * 用于设置 数据到 界面上
         *
         * @param wxChoiceData
         */
        void setWxChoiceData(WxChoiceListBean wxChoiceData);

        /**
         * 用于 设置异常数据到 界面上
         *
         * @param throwable
         */
        void setErrorData(Throwable throwable);

        /**
         * 用于设置 完成的 数据到界面上
         */
        void completeGetWxChoiceData();


    }

}
