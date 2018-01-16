package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/16 0016 23:02
 * description:
 */


public class AccountSetActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accountset);
        ButterKnife.bind(this);
        setTitle("账户设置");
    }

    @OnClick({R.id.tv_modify_login_pwd, R.id.tv_modify_transaction_pwd, R.id.tv_modify_phone})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_modify_login_pwd:
                startActivity(new Intent(act, ResetPwdActivity.class));
                break;
            case R.id.tv_modify_transaction_pwd:
                startActivity(new Intent(act, TransactionPwdActivity.class));
                break;
            case R.id.tv_modify_phone:
                startActivity(new Intent(act, TransactionPwdActivity.class));
                break;
        }
    }
}
