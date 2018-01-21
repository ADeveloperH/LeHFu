package com.jiefutong.lehfu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.widget.GifView;

/**
 * author：hj
 * time: 2018/1/21 0021 16:25
 * description:
 */


public class LoadMoreAdapter extends DelegateAdapter.Adapter<LoadMoreAdapter.ViewHolder> {

    private final Context mContext;

    public LoadMoreAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LoadMoreAdapter.ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.default_load_more, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvContent;
        private final GifView progressView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
            progressView = (GifView) itemView.findViewById(R.id.progress_view);
            progressView.setMovieResource(R.raw.loadmore);
        }
    }
}
