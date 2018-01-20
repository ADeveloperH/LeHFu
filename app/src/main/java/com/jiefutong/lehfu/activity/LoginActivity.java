package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseNotTitleActivity;
import com.jiefutong.lehfu.http.Http;
import com.jiefutong.lehfu.http.MyTextAsyncResponseHandler;
import com.jiefutong.lehfu.http.RequestParams;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/14 0014 15:28
 * description:
 */


public class LoginActivity extends BaseNotTitleActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(getResources().getColor(R.color.login_statusbar));
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_dynamiclogin, R.id.tv_forgetpwd, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_dynamiclogin:
                break;
            case R.id.tv_forgetpwd:
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        String phoneNumber = etPhone.getText().toString().trim();
        String codeStr = etCode.getText().toString().trim();
        String passwordStr = etPwd.getText().toString().trim();

        RequestParams requestParams = new RequestParams();
        requestParams.put("tel", phoneNumber);
        requestParams.put("verify", codeStr);
        requestParams.put("password", passwordStr);

        Http.post(Http.LOGIN_PWD, requestParams,
                new MyTextAsyncResponseHandler(act, "加载中..."){
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        Log.d("huang", "onSuccess: " + content);
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        Log.d("huang", "onFailure: " + error.getLocalizedMessage());
                    }
                });

    }
}
