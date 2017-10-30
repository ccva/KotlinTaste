package com.va.perfect.joke;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.va.perfect.R;
import com.va.perfect.fragment.BaseFragment;


/**
 * @author cjm
 */
public class JokeFragment extends BaseFragment {

    public static JokeFragment newInstance() {

        Bundle args = new Bundle();

        JokeFragment fragment = new JokeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public JokeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_joke, container, false);
    }

}
