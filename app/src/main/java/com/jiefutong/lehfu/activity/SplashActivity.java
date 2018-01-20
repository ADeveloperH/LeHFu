package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseNotTitleActivity;
import com.jiefutong.lehfu.utils.AppUtil;
import com.jiefutong.lehfu.utils.DateUtil;
import com.jiefutong.lehfu.utils.LoginUtils;
import com.jiefutong.lehfu.utils.SharedPreferencesUtil;

/**
 * author：hj
 * time: 2017/2/7 0007 13:05
 */

public class SplashActivity extends BaseNotTitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_layout);

        updateData();

        if (LoginUtils.isLoginState()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }
        finish();
    }

    private void updateData() {
        String appVersionName = AppUtil.getAppVersionName(this);
        String updateTime = SharedPreferencesUtil.getUpdateTime(appVersionName);
        if (TextUtils.isEmpty(updateTime)) {
            SharedPreferencesUtil.setUpdateTime(appVersionName,
                    DateUtil.getCurrentDateTime(DateUtil.formatStr_yyyyMMdd));
        }
    }
}
