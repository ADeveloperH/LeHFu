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

/**
 * author：hj
 * time: 2018/1/20 0020 22:31
 * description:
 */


public class HomeTitleAdapter extends DelegateAdapter.Adapter<HomeTitleAdapter.ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private int mCount = 0;
    private String mTitle;

    public HomeTitleAdapter(Context context, String mTitle,
                            LayoutHelper layoutHelper, int count) {
        this.mContext = context;
        this.mTitle = mTitle;
        this.mLayoutHelper = layoutHelper;
        this.mCount = count;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_info_title, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(mTitle);
        holder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("立即办卡".equals(mTitle)) {

                } else if ("金融头条".equals(mTitle)) {
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;
        private final TextView tvMore;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvMore = (TextView) itemView.findViewById(R.id.tv_more);

        }
    }
}
