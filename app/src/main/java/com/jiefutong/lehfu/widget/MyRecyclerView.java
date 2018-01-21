package com.jiefutong.lehfu.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * author：hj
 * time: 2018/1/21 0021 16:01
 * description:
 */


public class MyRecyclerView extends RecyclerView {
    private boolean onRefresh = false;
    private boolean onLoadMore = false;

    public MyRecyclerView(Context context) {
        super(context);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public synchronized boolean isOnRefresh() {
        return onRefresh;
    }

    public synchronized void setOnRefresh(boolean onRefresh) {
        this.onRefresh = onRefresh;
    }

    public synchronized boolean isOnLoadMore() {
        return onLoadMore;
    }

    public synchronized void setOnLoadMore(boolean onLoadMore) {
        this.onLoadMore = onLoadMore;
    }
}
