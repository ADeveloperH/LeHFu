package com.jiefutong.lehfu.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * author：hj
 * 所有Fragment的基类
 */
public class BaseFragment extends Fragment {
    public Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 保存Activity引用。防止出现Activity被回收时空指针
        this.mActivity = activity;
    }
}
