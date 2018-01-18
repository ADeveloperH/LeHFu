package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;
import com.jiefutong.lehfu.widget.AddPlanPopWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/18 0018 16:37
 * description:
 */


public class AddPlanActivity extends BaseTitleActivity {
    @BindView(R.id.tv_riqi)
    TextView tvRiqi;
    @BindView(R.id.ll_riqi)
    LinearLayout llRiqi;
    @BindView(R.id.ll_bishu)
    LinearLayout llBishu;
    private AddPlanPopWindow popWindow;
    private int curSelectDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addplan);
        ButterKnife.bind(this);
        setTitle("添加计划");
    }

    @OnClick({R.id.ll_riqi, R.id.ll_bishu, R.id.btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_riqi:
                showPop();
                break;
            case R.id.ll_bishu:
                break;
            case R.id.btn_next:
                startActivity(new Intent(act, RepaymentDetailActivity.class));
                break;
        }
    }

    private void showPop() {
        if (popWindow == null) {
            initPopWindow();
        }
        if (curSelectDate != 0) {
            popWindow.setCurPosition(curSelectDate - 1);
        } else {
            popWindow.setCurPosition(0);
        }
        popWindow.show(llRiqi);
    }

    private void initPopWindow() {
        popWindow = new AddPlanPopWindow(this);
        popWindow.setOnOkClickListener(new AddPlanPopWindow.OnOkClickListener() {
            @Override
            public void okClickLister(int selectPositoin, String selectStr) {
                if (selectPositoin >= 0 && !TextUtils.isEmpty(selectStr)) {
                    curSelectDate = selectPositoin + 1;
                    tvRiqi.setText(selectStr);
                }
            }
        });

    }
}
