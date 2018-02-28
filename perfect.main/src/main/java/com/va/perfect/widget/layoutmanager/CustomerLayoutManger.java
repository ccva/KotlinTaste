package com.va.perfect.widget.layoutmanager;

import android.support.v7.widget.RecyclerView;

/**
 * @author Junmeng.Chen
 * @date 2018/1/24
 */

public class CustomerLayoutManger extends RecyclerView.LayoutManager {

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return null;
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
    }

    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
    }
}
