package com.va.perfect.joke;

import com.va.perfect.net.dao.joke.JokeBean;

import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 *
 * Joke 接口连接
 */

public interface JokeContact {

    /**
     * 接口视图
     */
    interface JokeView{

        /**
         * 向View 层设置数据
         * @param jokeBeans
         */
        void setJokeListData(List<JokeBean> jokeBeans);

        /**
         * 向View 层 设置获取错误数据
         * @param throwable
         */
        void setJokeListDataError(Throwable throwable);

        /**
         * 通知View 层 获取数据完成
         */
        void setGetDataComplete();

    }

    /**
     * 中间层
     */
    interface JokePresenter{

        /**
         * 向数据层 请求数据
         */
        void getJokeListData();

        /**
         * 接收 数据层返回的数据
         * @param jokeBeans
         */
        void receiveJokeListData(List<JokeBean> jokeBeans);

        /**
         * 接收 数据层返回的数据
         * @param throwable
         */
        void receiveJokeListDataError(Throwable throwable);

        /**
         * 接收 数据层返回的数据
         */
        void receiveJokeListDataComplete();
    }


}
