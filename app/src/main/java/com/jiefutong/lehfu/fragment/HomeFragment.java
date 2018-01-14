package com.jiefutong.lehfu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseFragment;

/**
 * author：hj
 * time: 2018/1/14 0014 17:53
 * description:
 */


public class HomeFragment extends BaseFragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 如果有parent需要从parent删除，要不然会发生这个rootview已经有parent的错误。
        if (rootView != null) {
            ViewGroup parent = ((ViewGroup) rootView.getParent());
            if (parent != null) {
                parent.removeView(rootView);
            }
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
