package com.jiefutong.lehfu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.adapter.HomeGridAdapter;
import com.jiefutong.lehfu.adapter.HomeTitleAdapter;
import com.jiefutong.lehfu.adapter.HomeTopAdapter;
import com.jiefutong.lehfu.base.BaseFragment;
import com.jiefutong.lehfu.utils.UIUtils;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：hj
 * time: 2018/1/14 0014 17:53
 * description:
 */


public class HomeFragment extends BaseFragment {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private View rootView;

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
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRecyclerView();
    }

    private void initRecyclerView() {
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mActivity);
        recyclerview.setLayoutManager(layoutManager);
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);
        recyclerview.setAdapter(delegateAdapter);

        adapters.add(new HomeTopAdapter(mActivity, new LinearLayoutHelper(), 1));

        LinearLayoutHelper titleLayoutHelper = new LinearLayoutHelper();
        titleLayoutHelper.setMargin(0, 0, 0, UIUtils.dip2px(1));
        adapters.add(new HomeTitleAdapter(mActivity, "立即办卡", titleLayoutHelper, 1));

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2, 4);
        gridLayoutHelper.setBgColor(0xfff0f0f0);
        gridLayoutHelper.setVGap(UIUtils.dip2px(1));
        gridLayoutHelper.setHGap(UIUtils.dip2px(1));
        adapters.add(new HomeGridAdapter(mActivity, gridLayoutHelper));

        LinearLayoutHelper titleLayoutHelper2 = new LinearLayoutHelper();
        titleLayoutHelper2.setMargin(0, UIUtils.dip2px(12), 0, UIUtils.dip2px(1));
        adapters.add(new HomeTitleAdapter(mActivity, "金融头条", titleLayoutHelper2, 1));

        delegateAdapter.setAdapters(adapters);


    }
}
