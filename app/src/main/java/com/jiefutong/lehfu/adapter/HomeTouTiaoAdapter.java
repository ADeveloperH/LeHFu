package com.jiefutong.lehfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.bean.HomeTouTiaoResultBean;

import java.util.List;

/**
 * author：hj
 * time: 2018/1/20 0020 22:31
 * description:
 */


public class HomeTouTiaoAdapter extends DelegateAdapter.Adapter<HomeTouTiaoAdapter.ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;

    private List<HomeTouTiaoResultBean> dataList;

    public HomeTouTiaoAdapter(Context context, List<HomeTouTiaoResultBean> dataList,
                              LayoutHelper layoutHelper) {
        this.mContext = context;
        this.mLayoutHelper = layoutHelper;
        this.dataList = dataList;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home_toutiao, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HomeTouTiaoResultBean bean = dataList.get(position);
        holder.tvTitle.setText(bean.getTitle());
        holder.tvCreateTime.setText(bean.getCreate_time());
        holder.tvAccessCount.setText(bean.getCishu());
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final ImageView imageView;
        private final TextView tvCreateTime;
        private final TextView tvAccessCount;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvCreateTime = (TextView) itemView.findViewById(R.id.tv_create_time);
            tvAccessCount = (TextView) itemView.findViewById(R.id.tv_access_count);
        }
    }

    public void setDataList(List<HomeTouTiaoResultBean> dataList) {
        this.dataList = dataList;
    }
}
