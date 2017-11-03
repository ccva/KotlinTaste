package com.va.perfect.joke;

import com.va.perfect.model.BaseModel;
import com.va.perfect.net.constant.ApiConstant;
import com.va.perfect.net.retrofit.RetrofitService;
import com.va.perfect.util.RxSchedulers;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 */

public class JokeModel extends BaseModel {

    public void getJokeData(JokeContact.JokePresenter jokePresenter) {
        RetrofitService.juHeApi.getJokeList(ApiConstant.JOKE_SIGN_KEY)
                .map(new HandleResult<>())
                .compose(RxSchedulers.io_main())
                .subscribe(jokePresenter::receiveJokeListData,
                        jokePresenter::receiveJokeListDataError,
                        jokePresenter::receiveJokeListDataComplete);
    }

}
