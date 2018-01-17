package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;

/**
 * author：hj
 * time: 2018/1/17 0017 23:07
 * description:
 */


public class CreditCardConsumeActivity extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcardconsume);
        setTitle("信用卡消费");
    }
}
