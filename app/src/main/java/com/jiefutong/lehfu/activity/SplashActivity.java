package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseNotTitleActivity;

/**
 * author：hj
 * time: 2017/2/7 0007 13:05
 */

public class SplashActivity extends BaseNotTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);

        startActivity(new Intent(this,DynamicLoginActivity.class));
        finish();
    }
}
