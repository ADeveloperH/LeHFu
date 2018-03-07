package com.jiefutong.lehfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.bean.TeamListResultBean;
import com.jiefutong.lehfu.utils.DateUtil;

import java.util.List;

/**
 * author：hj
 * time: 2018/3/7 0007 09:34
 * description:
 */


public class TeamItemAdapter extends DelegateAdapter.Adapter<TeamItemAdapter.ViewHolder> {


    private final Context mContext;
    private final List<TeamListResultBean.InfoEntity.ListEntity> list;
    private final LayoutHelper mLayoutHelper;

    public TeamItemAdapter(Context context, List<TeamListResultBean.InfoEntity.ListEntity> list,
                           LayoutHelper layoutHelper) {
        this.mContext = context;
        this.list = list;
        this.mLayoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public TeamItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TeamItemAdapter.ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_team, parent, false));
    }

    @Override
    public void onBindViewHolder(TeamItemAdapter.ViewHolder holder, int position) {
        TeamListResultBean.InfoEntity.ListEntity listEntity = list.get(position);

        holder.tvName.setText(listEntity.getTruename());
        holder.tvLevel.setText("53".equals(listEntity.getPid()) ? "直推" : "健推");
        holder.tvTime.setText(DateUtil.transferLongToDateStr(Long.parseLong(listEntity.getRegister_time()) * 1000
                , DateUtil.formatStr_yyyyMMdd));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvLevel;
        private final TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvLevel = (TextView) itemView.findViewById(R.id.tv_level);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }
}
