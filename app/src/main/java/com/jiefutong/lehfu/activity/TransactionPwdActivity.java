package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.imageloader.ImageLoaderUtil;
import com.imageloader.mode.DiskCacheMode;
import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;
import com.jiefutong.lehfu.bean.SimpleResultBean;
import com.jiefutong.lehfu.http.Http;
import com.jiefutong.lehfu.http.MyTextAsyncResponseHandler;
import com.jiefutong.lehfu.http.RequestParams;
import com.jiefutong.lehfu.utils.JsonUtil;
import com.jiefutong.lehfu.utils.ToastUtils;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/15 0015 01:01
 * description:
 */


public class TransactionPwdActivity extends BaseTitleActivity {
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.iv_code)
    ImageView ivCode;
    @BindView(R.id.et_sms_code)
    EditText etSmsCode;
    @BindView(R.id.tv_get_sms_code)
    TextView tvGetSmsCode;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;
    @BindView(R.id.btn_ok)
    Button btnOk;
    private String uuid;
    private String inputCode;
    private String inputSmsCode;
    private String inputPwd1;
    private String inputPwd2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionpwd);
        ButterKnife.bind(this);
        setTitle("交易密码设置");
        tvPhone.setText(phoneNumber);

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
//                .placeholder(R.drawable.captcha_default)
//                .error(R.drawable.captcha_default)
                .into(ivCode);
    }

    @OnClick({R.id.iv_code, R.id.tv_get_sms_code, R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_code:
                getCode();
                etCode.setText("");
                break;
            case R.id.tv_get_sms_code:
                getSmsCode();
                break;
            case R.id.btn_ok:
                resetPwd();
                break;
        }
    }


    /**
     * 重置密码
     */
    private void resetPwd() {
        updateInputInfo();
        if (TextUtils.isEmpty(inputCode)) {
            ToastUtils.showCenterLongToast("请输入图形验证码");
            return;
        }

        if (TextUtils.isEmpty(inputSmsCode)) {
            ToastUtils.showCenterLongToast("请输入短信验证码");
            return;
        }

        if (TextUtils.isEmpty(inputPwd1)
                || TextUtils.isEmpty(inputPwd2)) {
            ToastUtils.showCenterLongToast("请输入密码");
            return;
        }

        if (!inputPwd1.equals(inputPwd2)) {
            ToastUtils.showCenterLongToast("两次输入的密码不一致");
            return;
        }
        final RequestParams requestParams = new RequestParams();
        requestParams.put("tel", phoneNumber);
        requestParams.put("uuid", uuid);
        requestParams.put("verify", inputCode);
        requestParams.put("smsnum", inputSmsCode);
        requestParams.put("password", inputPwd1);
        requestParams.put("password2", inputPwd2);
        Http.post(Http.RESET_TRANSACTIONPWD, requestParams,
                new MyTextAsyncResponseHandler(act, "重置中...") {

                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        SimpleResultBean resultBean = JsonUtil.fromJson(content, SimpleResultBean.class);
                        if (resultBean.getStatus() == 1) {
                            finish();
                        }
                        ToastUtils.showCenterShortToast(resultBean.getInfo());
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        ToastUtils.showCenterShortToast("重置密码失败");
                    }
                });
    }


    /**
     * 获取短信验证码
     */
    private void getSmsCode() {
        updateInputInfo();

        if (TextUtils.isEmpty(inputCode)) {
            ToastUtils.showCenterLongToast("请输入图形验证码");
            return;
        }
        RequestParams requestParams = new RequestParams();
        requestParams.put("phone", phoneNumber);
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
        inputCode = etCode.getText().toString().trim();
        inputSmsCode = etSmsCode.getText().toString().trim();
        inputPwd1 = etPwd.getText().toString().trim();
        inputPwd2 = etPwd2.getText().toString().trim();
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
