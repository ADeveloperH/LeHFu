package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author：hj
 * time: 2018/1/15 0015 21:22
 * description:
 */


public class IncomeDetailActivity extends BaseTitleActivity {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incomedetail);
        ButterKnife.bind(this);
        setTitle("收益明细");
    }
}
