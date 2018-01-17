package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;
import com.jiefutong.lehfu.utils.DialogUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/17 0017 21:48
 * description:
 */


public class TiXianByBalanceActivity extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tixianbybalance);
        ButterKnife.bind(this);
        setTitle("余额提现");
    }

    @OnClick({R.id.rl_selectbank, R.id.btn_ti_xian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_selectbank:
                startActivity(new Intent(act, SelectBankCardActivity.class));
                break;
            case R.id.btn_ti_xian:
                showRemindDialog();
                break;
        }
    }

    private void showRemindDialog() {
        DialogUtil.getSimpleDialog(act, "设置交易密码",
                "请先设置交易密码", "去设置",
                "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(act, TransactionPwdActivity.class));
                    }
                }, null, true).show();
    }
}
