package com.va.perfect.tv.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;
import com.va.perfect.net.dao.result.CategoryBean;

import java.util.List;

/**
 * @author cjm
 * @date 17-10-29
 */

public class CategoryAdapter extends BaseRecyclerAdapter<CategoryBean> {


    public CategoryAdapter(Context mContext, List<CategoryBean> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(mLayoutInflater.inflate(R.layout.item_category, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        TextView tvName = holder.itemView.findViewById(R.id.tv_name);

        CategoryBean categoryBean = mDataList.get(position);
        tvName.setText(categoryBean.getName());
    }


}