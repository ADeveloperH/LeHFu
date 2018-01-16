package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseNotTitleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/16 0016 20:42
 * description:
 */


public class SpreadIncomeActivity extends BaseNotTitleActivity {

    @BindView(R.id.btn_ming_xi)
    Button btnMingXi;
    @BindView(R.id.btn_ti_xian)
    Button btnTiXian;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spreadincom);
        ButterKnife.bind(this);

        WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
        localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | localLayoutParams.flags);

    }

    @OnClick({R.id.titlebar_back, R.id.btn_ming_xi, R.id.btn_ti_xian})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_back:
                finishActivity();
                break;
            case R.id.btn_ming_xi:
                startActivity(new Intent(act, IncomeDetailActivity.class));
                break;
            case R.id.btn_ti_xian:
                break;
        }
    }
}
