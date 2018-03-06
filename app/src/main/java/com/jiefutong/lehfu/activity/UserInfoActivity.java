package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jiefutong.lehfu.AppManager;
import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;
import com.jiefutong.lehfu.bean.SimpleResultBean;
import com.jiefutong.lehfu.http.Http;
import com.jiefutong.lehfu.http.MyTextAsyncResponseHandler;
import com.jiefutong.lehfu.http.RequestParams;
import com.jiefutong.lehfu.utils.JsonUtil;
import com.jiefutong.lehfu.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/15 0015 00:52
 * description:
 */


public class UserInfoActivity extends BaseTitleActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        ButterKnife.bind(this);
        setTitle("个人信息");
    }

    @OnClick({R.id.ll_certification_state, R.id.btn_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_certification_state:
                startActivity(new Intent(this, CertificationActivity.class));
                break;
            case R.id.btn_logout:
                logout();
                break;
        }

    }

    /**
     * 退出登录
     */
    private void logout() {
        RequestParams requestParams = new RequestParams();
        Http.post(Http.LOGOUT, requestParams,
                new MyTextAsyncResponseHandler(act, "退出登录中...") {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        SimpleResultBean resultBean = JsonUtil.fromJson(content, SimpleResultBean.class);
                        ToastUtils.showCenterShortToast(resultBean.getInfo());
                        if (resultBean.getStatus() == 1) {
                            AppManager.getAppManager().finishAllActivity();
                            startActivity(new Intent(act, LoginActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        ToastUtils.showCenterShortToast("退出登录失败");
                    }
                });
    }
}
