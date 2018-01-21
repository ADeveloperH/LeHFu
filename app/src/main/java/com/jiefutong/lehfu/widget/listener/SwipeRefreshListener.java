package com.jiefutong.lehfu.widget.listener;

import android.support.v4.widget.SwipeRefreshLayout;

import com.jiefutong.lehfu.widget.MyRecyclerView;

/**
 * author：hj
 * time: 2018/1/21 0021 15:38
 * description:
 */


public abstract class SwipeRefreshListener implements SwipeRefreshLayout.OnRefreshListener {

    private final MyRecyclerView mRecyclerView;

    public SwipeRefreshListener(MyRecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    protected abstract void refresh();

    @Override
    public void onRefresh() {
        if (mRecyclerView != null) {
            if (!mRecyclerView.isOnLoadMore() && !mRecyclerView.isOnRefresh()) {
                //没有加载更多，执行刷新
                mRecyclerView.setOnRefresh(true);
                refresh();
            }
        }
    }
}
