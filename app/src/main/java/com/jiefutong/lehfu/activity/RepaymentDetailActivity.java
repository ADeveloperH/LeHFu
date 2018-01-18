package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/18 0018 21:16
 * description:
 */


public class RepaymentDetailActivity extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repaymentdetail);
        ButterKnife.bind(this);
        setTitle("还款明细");
    }

    @OnClick(R.id.btn_next)
    public void onClick() {
        startActivity(new Intent(act, PayMoneyActivity.class));
    }
}
