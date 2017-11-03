package com.va.perfect.joke;

import com.va.perfect.net.dao.joke.JokeBean;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/11/3
 */

public class JokePresenterImpl implements JokeContact.JokePresenter {

    private WeakReference<JokeContact.JokeView> jokeView;

    private JokeModel jokeModel;

    public JokePresenterImpl(JokeContact.JokeView jokeView) {
        this.jokeView = new WeakReference<>(jokeView);
        this.jokeModel = new JokeModel();
    }

    @Override
    public void getJokeListData() {
        jokeModel.getJokeData(this);
    }

    @Override
    public void receiveJokeListData(List<JokeBean> jokeBeans) {
        jokeView.get().setJokeListData(jokeBeans);
    }

    @Override
    public void receiveJokeListDataError(Throwable throwable) {
        jokeView.get().setJokeListDataError(throwable);
    }

    @Override
    public void receiveJokeListDataComplete() {
        jokeView.get().setGetDataComplete();
    }
}
