package com.jiefutong.lehfu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseFragment;
import com.jiefutong.lehfu.bean.CardListResultBean;
import com.jiefutong.lehfu.widget.MyRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：hj
 * time: 2018/1/18 0018 14:36
 * description:
 */


public class ChuXuCardFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    MyRecyclerView recyclerview;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    private View rootView;

    public static ChuXuCardFragment newInstance() {
        ChuXuCardFragment fragment = new ChuXuCardFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
//        if (rootView != null) {
//            ViewGroup parent = ((ViewGroup) rootView.getParent());
//            if (parent != null) {
//                parent.removeView(rootView);
//            }
//            return rootView;
//        }
        rootView = inflater.inflate(R.layout.fragment_chuxucard, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    public void refreshData(List<CardListResultBean.InfoEntity.BlistEntity> blist) {

    }
}
