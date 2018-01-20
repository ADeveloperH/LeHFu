package com.jiefutong.lehfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jiefutong.lehfu.R;

/**
 * author：hj
 * time: 2018/1/20 0020 22:31
 * description:
 */


public class HomeGridAdapter extends DelegateAdapter.Adapter<HomeGridAdapter.ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;

    private String[] titles = {"中信银行", "民生银行", "交通银行", "平安银行",};
    private String[] descs = {"年轻不重样！百..", "金融商圈扫码最..", "周五加油5%优惠", "淘宝积分，还能.."};

    public HomeGridAdapter(Context context, LayoutHelper layoutHelper) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(titles[position]);
        holder.tvDesc.setText(descs[position]);
    }

    @Override
    public int getItemCount() {
        Log.d("huang", "getItemCount: mLayoutHelper.getItemCount():" + mLayoutHelper.getItemCount());
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvDesc;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);

        }
    }
}
