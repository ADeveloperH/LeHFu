package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;

/**
 * author：hj
 * time: 2018/1/14 0014 13:01
 * description:
 */


public class RegisterActivity extends BaseTitleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regitster);
        setTitle("注册");
    }
}
