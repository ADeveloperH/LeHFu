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
 * time: 2018/1/18 0018 21:29
 * description:
 */


public class PayMoneyActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymoney);
        ButterKnife.bind(this);
        setTitle("支付执行金额");
    }

    @OnClick(R.id.btn_ok)
    public void onClick() {
        startActivity(new Intent(act, MainActivity.class));
    }
}
