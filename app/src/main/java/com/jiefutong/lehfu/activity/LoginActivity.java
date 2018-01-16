package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseNotTitleActivity;

/**
 * author：hj
 * time: 2018/1/14 0014 15:28
 * description:
 */


public class LoginActivity extends BaseNotTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(getResources().getColor(R.color.login_statusbar));
        setContentView(R.layout.activity_login);
    }
}
