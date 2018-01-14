package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;

/**
 * author：hj
 * time: 2018/1/15 0015 01:01
 * description:
 */


public class ResetPwdActivity extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpwd);
        setTitle("登录密码重置");
    }
}
