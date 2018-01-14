package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.TextView;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;
import com.jiefutong.lehfu.utils.AppUtil;
import com.jiefutong.lehfu.utils.DateUtil;
import com.jiefutong.lehfu.utils.SharedPreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：hj
 * time: 2018/1/15 0015 00:09
 * description:
 */


public class AboutActivity extends BaseTitleActivity {
    @BindView(R.id.tv_version_name)
    TextView tvVersionName;
    @BindView(R.id.tv_update_time)
    TextView tvUpdateTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);
        setTitle("关于我们");

        String appVersionName = AppUtil.getAppVersionName(this);
        tvVersionName.setText("V" + appVersionName);
        String updateTime = SharedPreferencesUtil.getUpdateTime(appVersionName);
        if (TextUtils.isEmpty(updateTime)) {
            updateTime = DateUtil.getCurrentDateTime(DateUtil.formatStr_yyyyMMdd);
            SharedPreferencesUtil.setUpdateTime(appVersionName,
                    updateTime);
        }
        tvUpdateTime.setText("更新时间：" + updateTime);
    }
}
