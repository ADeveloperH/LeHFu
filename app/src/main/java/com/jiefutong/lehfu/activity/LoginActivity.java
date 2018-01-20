package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.imageloader.ImageLoaderUtil;
import com.imageloader.mode.DiskCacheMode;
import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseNotTitleActivity;
import com.jiefutong.lehfu.bean.SimpleResultBean;
import com.jiefutong.lehfu.http.Http;
import com.jiefutong.lehfu.http.MyTextAsyncResponseHandler;
import com.jiefutong.lehfu.http.RequestParams;
import com.jiefutong.lehfu.utils.DataCheckUtils;
import com.jiefutong.lehfu.utils.JsonUtil;
import com.jiefutong.lehfu.utils.SharedPreferencesUtil;
import com.jiefutong.lehfu.utils.ToastUtils;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/14 0014 15:28
 * description:
 */


public class LoginActivity extends BaseNotTitleActivity {
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    private String uuid;
    private String inputPhone;
    private String inputCode;
    private String inputPwd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(getResources().getColor(R.color.login_statusbar));
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        getCode();
    }


    /**
     * 获取图片验证码
     */
    private void getCode() {
        uuid = UUID.randomUUID().toString();
        String captchaPath = Http.BASE_URL + Http.GET_CODE + "?uuid=" + uuid;
        ImageLoaderUtil.with(this)
                .load(captchaPath)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheMode.NONE)
                .into(ivCode);
    }

    @OnClick({R.id.tv_dynamiclogin, R.id.tv_forgetpwd, R.id.btn_login, R.id.iv_code, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_dynamiclogin:
                startActivity(new Intent(act, DynamicLoginActivity.class));
                break;
            case R.id.tv_forgetpwd:
                startActivity(new Intent(act, FindPwdActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(act, RegisterActivity.class));
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.iv_code:
                getCode();
                etCode.setText("");
                break;
        }
    }

    /**
     * 注册
     */
    private void login() {
        updateInputInfo();
        if (!DataCheckUtils.isTelPhone(inputPhone)) {
            ToastUtils.showCenterLongToast("请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(inputCode)) {
            ToastUtils.showCenterLongToast("请输入图形验证码");
            return;
        }

        if (TextUtils.isEmpty(inputPwd)) {
            ToastUtils.showCenterLongToast("请输入密码");
            return;
        }
        RequestParams requestParams = new RequestParams();
        requestParams.put("tel", inputPhone);
        requestParams.put("verify", inputCode);
        requestParams.put("password", inputPwd);
        requestParams.put("uuid", uuid);

        Http.post(Http.LOGIN_PWD, requestParams,
                new MyTextAsyncResponseHandler(act, "登录中...") {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        SimpleResultBean resultBean = JsonUtil.fromJson(content, SimpleResultBean.class);
                        if (resultBean.getStatus() == 1) {
                            SharedPreferencesUtil.setPhoneNumber(inputPhone);
                            startActivity(new Intent(act, MainActivity.class));
                            finish();
                        }
                        ToastUtils.showCenterShortToast(resultBean.getInfo());
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        ToastUtils.showCenterShortToast("登录失败");
                    }
                });

    }

    /**
     * 获取输入的信息
     */
    private void updateInputInfo() {
        inputPhone = etPhone.getText().toString().trim();
        inputCode = etCode.getText().toString().trim();
        inputPwd = etPwd.getText().toString().trim();
    }

}
