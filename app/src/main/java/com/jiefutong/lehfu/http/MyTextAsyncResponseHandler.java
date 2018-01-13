package com.jiefutong.lehfu.http;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;

import com.jiefutong.lehfu.AppManager;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class MyTextAsyncResponseHandler extends MyCallback {
    private static final String TAG = "MyTextAsyncResponseHandler";

    protected Context context;
    private ProgressDialog loading;
    private String loadingMessage;
    private boolean cancel;

    public MyTextAsyncResponseHandler(Context context, String loadingMessage) {
        if (null == context) {
            throw new IllegalArgumentException("context can't be null");
        }
        this.context = context;
        this.loadingMessage = loadingMessage;
        onStart();
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        super.onResponse(call, response);
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        super.onFailure(call, t);
    }

    @Override
    public void onStart() {
        if (!TextUtils.isEmpty(loadingMessage)) {
            try {
                if(null!=context){
                    loading = ProgressDialog.show(context, "", loadingMessage, true, false , new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            try {
                                if(null!=context){
                                    loading.dismiss();
                                    cancel = true;
                                }
                            } catch (Exception e) {
                            }
                        }
                    });

                    loading.setOnKeyListener(new DialogInterface.OnKeyListener() {

                        @Override
                        public boolean onKey(DialogInterface dialog,
                                             int keyCode, KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_BACK) {
                                loading.dismiss();
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            cancel = false;
        }
    }

    @Override
    public void onSuccess(String content) {
        /**
         * 这里判断，如果此时的token已经失效了就提醒账户重新登录
         */
        if(!TextUtils.isEmpty(content)){
            try {
                JSONObject jsonObject = new JSONObject(content);
                if(jsonObject.has("returnCode")){
                    String returnCode = jsonObject.getString("returnCode");
                    String returnMessage = jsonObject
                            .getString("returnMessage");
                    if ("-101".equals(returnCode)) {
                        //用户下线
//                        ToastUtils.showShortToast(returnMessage);
                        AppManager.getAppManager().finishAllActivity();
//                        context.startActivity(new Intent(context, LoginActivity.class));
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

    @Override
    public void onFailure(Throwable error) {
    }

    @Override
    public void onFinish() {
        try {
            if (!TextUtils.isEmpty(loadingMessage)) {
                if(context instanceof Activity){
                    Activity activity = (Activity) context;
                    if(!activity.isFinishing() && loading != null && loading.isShowing()){
                        loading.dismiss();
                    }
                }else{
                    if(loading != null && loading.isShowing()){
                        loading.dismiss();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
