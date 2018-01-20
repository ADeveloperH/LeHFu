package com.jiefutong.lehfu.http.factory;

import com.jiefutong.lehfu.utils.SharedPreferencesUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/8/23 0023.
 */
public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        //添加请求头信息
        Request.Builder headerBuilder = original.newBuilder();
        headerBuilder.addHeader("APPTOKEN", SharedPreferencesUtil.getAppToken());
        return chain.proceed(headerBuilder.build());
    }
}
