package com.jiefutong.lehfu.widget.listener;

import android.support.v7.widget.RecyclerView;

import com.jiefutong.lehfu.widget.MyRecyclerView;

/**
 * author：hj
 * time: 2018/1/21 0021 15:33
 * description:
 */


public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private final MyRecyclerView mRecyclerView;

    public RecyclerViewScrollListener(MyRecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    protected abstract void loadMore();

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (!recyclerView.canScrollVertically(1)) {
            //到底了
            if (mRecyclerView != null) {
                if (!mRecyclerView.isOnRefresh()) {
                    //没有正在刷新。执行加载更多
                    mRecyclerView.setOnLoadMore(true);
                    loadMore();
                }
            }
        }
    }
}
