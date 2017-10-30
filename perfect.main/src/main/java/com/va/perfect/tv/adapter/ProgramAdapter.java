package com.va.perfect.tv.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.va.perfect.R;
import com.va.perfect.base.adapter.BaseRecyclerAdapter;
import com.va.perfect.base.adapter.BaseRecyclerViewHolder;
import com.va.perfect.net.dao.result.ProgramBean;

import java.util.List;

/**
 * @author Junmeng.Chen
 * @date 2017/10/30
 */

public class ProgramAdapter extends BaseRecyclerAdapter<ProgramBean> {

    public ProgramAdapter(Context mContext, List<ProgramBean> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerViewHolder(mLayoutInflater.inflate(R.layout.item_program, parent,false));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        TextView tvName = holder.itemView.findViewById(R.id.tv_program_name);
        TextView tvTime = holder.itemView.findViewById(R.id.tv_program_time);

        ProgramBean programBean = mDataList.get(position);
        tvName.setText(programBean.getPName());
        tvTime.setText(programBean.getTime());
    }
}
