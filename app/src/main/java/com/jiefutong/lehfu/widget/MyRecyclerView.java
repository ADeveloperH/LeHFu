package com.jiefutong.lehfu.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.jiefutong.lehfu.adapter.LoadMoreAdapter;

/**
 * author：hj
 * time: 2018/1/21 0021 16:01
 * description:
 */


public class MyRecyclerView extends RecyclerView {
    private final Context context;
    private boolean onRefresh = false;
    private boolean onLoadMore = false;

    //是否添加了加载更多的adapter
    private boolean hasLoadMoreAdapter = false;
    private LoadMoreAdapter loadMoreAdapter;
    private ViewGroup refreshLayout;

    public MyRecyclerView(Context context) {
        this(context, null);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public synchronized boolean isOnRefresh() {
        return onRefresh;
    }

    public synchronized void setOnRefresh(boolean onRefresh) {
        this.onRefresh = onRefresh;
        if (!onRefresh && refreshLayout != null) {
            if (refreshLayout instanceof SwipeRefreshLayout) {
                SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout) refreshLayout;
                if (swipeRefresh != null && swipeRefresh.isRefreshing()) {
                    swipeRefresh.setRefreshing(false);
                }
            }

        }
    }

    public synchronized boolean isOnLoadMore() {
        return onLoadMore;
    }

    public synchronized void setOnLoadMore(boolean onLoadMore) {
        this.onLoadMore = onLoadMore;
        Adapter adapter = getAdapter();
        if (adapter instanceof DelegateAdapter) {
            DelegateAdapter delegateAdapter = (DelegateAdapter) adapter;
            if (onLoadMore) {
                if (!hasLoadMoreAdapter) {
                    if (loadMoreAdapter == null) {
                        loadMoreAdapter = new LoadMoreAdapter(context);
                    }
                    delegateAdapter.addAdapter(loadMoreAdapter);
                    hasLoadMoreAdapter = true;
                    int position = delegateAdapter.getItemCount() - 1;
                    delegateAdapter.notifyItemInserted(position);
                    scrollToPosition(position);
                }
            } else {
                if (hasLoadMoreAdapter) {
                    delegateAdapter.removeLastAdapter();
                    hasLoadMoreAdapter = false;
                }
            }

        }
    }

    /**
     * 设置下拉刷新的控件
     * @param refreshLayout
     */
    public void setRefreshLayout(ViewGroup refreshLayout) {
        this.refreshLayout = refreshLayout;
    }
}
