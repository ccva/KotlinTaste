package com.va.perfect.base.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by cjm on 17-10-29.
 */

public abstract class BaseSimpleAdapter<T> extends BaseAdapter {

    private List<T> mDataList;

    public BaseSimpleAdapter() {
    }

    public void setmDataList(List<T> mDataList) {
        this.mDataList = mDataList;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataList == null ? null : mDataList.get(position);
    }

    @Override
    public int getViewTypeCount() {

        addItemViewType();

        return super.getViewTypeCount();
    }

    protected abstract void addItemViewType();

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        T t = mDataList.get(position);

        return null;
    }
}
