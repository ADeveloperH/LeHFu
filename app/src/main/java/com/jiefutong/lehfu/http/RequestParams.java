package com.jiefutong.lehfu.http;


import android.text.TextUtils;

import com.jiefutong.lehfu.utils.RSAEncrypt;

import java.util.HashMap;

/**
 * 默认升序
 */
public class RequestParams extends HashMap<String, String> {
    @Override
    public String put(String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            value = RSAEncrypt.encrypt(value.getBytes());
        } else {
            value = "";
        }
        return super.put(key, value);
    }
}
