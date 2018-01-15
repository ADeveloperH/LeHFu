package com.jiefutong.lehfu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.activity.AboutActivity;
import com.jiefutong.lehfu.activity.IncomeDetailActivity;
import com.jiefutong.lehfu.activity.UserInfoActivity;
import com.jiefutong.lehfu.base.BaseFragment;
import com.jiefutong.lehfu.widget.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/14 0014 17:53
 * description:
 */


public class CustomerFragment extends BaseFragment {

    @BindView(R.id.iv_user_icon)
    CircleImageView ivUserIcon;
    @BindView(R.id.ll_customer_info)
    LinearLayout llCustomerInfo;
    @BindView(R.id.ll_yu_e)
    LinearLayout llYuE;
    @BindView(R.id.ll_shou_yi)
    LinearLayout llShouYi;
    @BindView(R.id.tv_ti_xian)
    TextView tvTiXian;
    @BindView(R.id.tv_ka_bao)
    TextView tvKaBao;
    @BindView(R.id.tv_shou_yi)
    TextView tvShouYi;
    @BindView(R.id.tv_my_team)
    TextView tvMyTeam;
    @BindView(R.id.tv_account_set)
    TextView tvAccountSet;
    @BindView(R.id.tv_want_tui_guang)
    TextView tvWantTuiGuang;
    @BindView(R.id.tv_ming_xi)
    TextView tvMingXi;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.tv_feed_back)
    TextView tvFeedBack;
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
        rootView = inflater.inflate(R.layout.fragment_customer, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick({R.id.iv_user_icon, R.id.ll_customer_info, R.id.ll_yu_e, R.id.ll_shou_yi,
            R.id.tv_ti_xian, R.id.tv_ka_bao, R.id.tv_shou_yi, R.id.tv_my_team, R.id.tv_account_set,
            R.id.tv_want_tui_guang, R.id.tv_ming_xi, R.id.tv_about, R.id.tv_feed_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                break;
            case R.id.ll_customer_info:
                startActivity(new Intent(mActivity, UserInfoActivity.class));
                break;
            case R.id.ll_yu_e:
                break;
            case R.id.ll_shou_yi:
                startActivity(new Intent(mActivity, IncomeDetailActivity.class));
                break;
            case R.id.tv_ti_xian:
                break;
            case R.id.tv_ka_bao:
                break;
            case R.id.tv_shou_yi:
                break;
            case R.id.tv_my_team:
                break;
            case R.id.tv_account_set:
                break;
            case R.id.tv_want_tui_guang:
                break;
            case R.id.tv_ming_xi:
                break;
            case R.id.tv_about:
                startActivity(new Intent(mActivity, AboutActivity.class));
                break;
            case R.id.tv_feed_back:
                break;
        }
    }
}
