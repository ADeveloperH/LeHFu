package com.jiefutong.lehfu.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.jiefutong.lehfu.R;
import com.jiefutong.lehfu.base.BaseTitleActivity;
import com.jiefutong.lehfu.bean.SimpleResultBean;
import com.jiefutong.lehfu.http.Http;
import com.jiefutong.lehfu.http.MyTextAsyncResponseHandler;
import com.jiefutong.lehfu.http.RequestParams;
import com.jiefutong.lehfu.utils.JsonUtil;
import com.jiefutong.lehfu.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author：hj
 * time: 2018/1/16 0016 21:32
 * description:
 */


public class FeedBackActivity extends BaseTitleActivity {
    @BindView(R.id.et_content)
    EditText etContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivty_feedback);
        ButterKnife.bind(this);
        setTitle("问题反馈");
    }

    @OnClick(R.id.btn_submit)
    public void onClick() {
        submitFeedBack();
    }

    /**
     * 提交反馈信息
     */
    private void submitFeedBack() {
        String content = etContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showCenterShortToast("请输入反馈内容");
            return;
        }
        RequestParams requestParams = new RequestParams();
        requestParams.put("content", content);
        Http.post(Http.FEEDBACK, requestParams,
                new MyTextAsyncResponseHandler(act, "提交中...") {
                    @Override
                    public void onSuccess(String content) {
                        super.onSuccess(content);
                        SimpleResultBean resultBean = JsonUtil.fromJson(content, SimpleResultBean.class);
                        ToastUtils.showCenterShortToast(resultBean.getInfo());
                        if (resultBean.getStatus() == 1) {
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Throwable error) {
                        super.onFailure(error);
                        ToastUtils.showCenterShortToast("提交失败");
                    }
                });
    }
}
