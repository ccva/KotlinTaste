package com.va.perfect.fragment;

import android.support.v4.app.Fragment;

/**
 * @author Junmeng.Chen
 * @date 2017/10/20
 */

public abstract class BaseFragment extends Fragment {

    private boolean needLazyLoading = true;

    private boolean needLoadOnStart = true;

    public boolean isNeedLazyLoading() {
        return needLazyLoading;
    }

    public void setNeedLazyLoading(boolean needLazyLoading) {
        this.needLazyLoading = needLazyLoading;
    }

    public boolean isNeedLoadOnStart() {
        return needLoadOnStart;
    }

    public void setNeedLoadOnStart(boolean needLoadOnStart) {
        this.needLoadOnStart = needLoadOnStart;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (needLoadOnStart) {

            if (needLazyLoading) {

                if (getUserVisibleHint()) {
                    refreshData();
                }

            }

        }


    }

    /**
     * 在onStart的生命周期 刷新数据
     */
    protected void refreshData() {
    }

}
