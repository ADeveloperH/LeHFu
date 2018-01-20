package com.jiefutong.lehfu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/14 0014 15:58
 * description:
 */


public class DynamicLoginActivity extends BaseNotTitleActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.et_sms_code)
    EditText etSmsCode;
    @BindView(R.id.tv_get_sms_code)
    TextView tvGetSmsCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    private String uuid;
    private String inputPhone;
    private String inputCode;
    private String inputSmsCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBarColor(getResources().getColor(R.color.login_statusbar));
        setContentView(R.layout.activity_dynamiclogin);
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

    @OnClick({R.id.iv_code, R.id.tv_get_sms_code, R.id.tv_login, R.id.tv_forgetpwd, R.id.btn_login, R.id.tv_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_code:
                getCode();
                etCode.setText("");
                break;
            case R.id.tv_get_sms_code:
                getSmsCode();
                break;
            case R.id.tv_login:
                startActivity(new Intent(act, LoginActivity.class));
                break;
            case R.id.tv_forgetpwd:
                startActivity(new Intent(act, FindPwdActivity.class));
                break;
            case R.id.btn_login:
                login();
                break;
            case R.id.tv_register:
                startActivity(new Intent(act, RegisterActivity.class));
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

        if (TextUtils.isEmpty(inputSmsCode)) {
            ToastUtils.showCenterLongToast("请输入短信验证码");
            return;
        }
        RequestParams requestParams = new RequestParams();
        requestParams.put("tel", inputPhone);
        requestParams.put("verify", inputCode);
        requestParams.put("smsnum", inputSmsCode);
        requestParams.put("uuid", uuid);

        Http.post(Http.LOGIN_SMS, requestParams,
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
     * 获取短信验证码
     */
    private void getSmsCode() {
        updateInputInfo();
        if (!DataCheckUtils.isTelPhone(inputPhone)) {
            ToastUtils.showCenterLongToast("请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(inputCode)) {
            ToastUtils.showCenterLongToast("请输入图形验证码");
            return;
        }
        RequestParams requestParams = new RequestParams();
        requestParams.put("phone", inputPhone);
        requestParams.put("uuid", uuid);
        requestParams.put("verify", inputCode);
        Http.post(Http.GET_OTHER_SMS_CODE, requestParams,
                new MyTextAsyncResponseHandler(act, "获取中...") {

                    @Override
                    public void onStart() {
                        super.onStart();
                        tvGetSmsCode.setEnabled(false);
                    }

                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        SimpleResultBean resultBean = JsonUtil.fromJson(content, SimpleResultBean.class);
                        if (resultBean.getStatus() == 1) {
                            startTimer();
                        } else {
                            tvGetSmsCode.setEnabled(true);
                        }
                        ToastUtils.showCenterShortToast(resultBean.getInfo());
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        ToastUtils.showCenterShortToast("获取失败");
                        tvGetSmsCode.setEnabled(true);
                    }
                });
    }

    /**
     * 获取输入的信息
     */
    private void updateInputInfo() {
        inputPhone = etPhone.getText().toString().trim();
        inputCode = etCode.getText().toString().trim();
        inputSmsCode = etSmsCode.getText().toString().trim();
    }


    Timer timer = null;

    private void startTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new Timer();
        tvGetSmsCode.setEnabled(false);
        TimerTask timerTask = new TimerTask() {
            int i = 120;

            @Override
            public void run() {
                i--;
                Message message = Message.obtain();
                message.arg1 = i;
                message.what = 0;
                handler.sendMessage(message);
            }
        };
        timer.schedule(timerTask, 0, 1 * 1000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    tvGetSmsCode.setText("(" + msg.arg1 + ")" + "秒");
                    if (msg.arg1 <= 0) {
                        tvGetSmsCode.setEnabled(true);
                        tvGetSmsCode.setText("点击获取");
                        if (timer != null)
                            timer.cancel();
                    }
                    break;
                default:
                    break;
            }
        }
    };
}
