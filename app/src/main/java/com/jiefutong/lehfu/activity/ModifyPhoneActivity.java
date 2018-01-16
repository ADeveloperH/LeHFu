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
 * time: 2018/1/16 0016 23:44
 * description:
 */


public class ModifyPhoneActivity extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifyphone);
        ButterKnife.bind(this);
        setTitle("手机号修改");
    }

    @OnClick(R.id.tv_cannot_sms)
    public void onClick() {
        startActivity(new Intent(act, ModifyPhoneActivity2.class));
    }
}
