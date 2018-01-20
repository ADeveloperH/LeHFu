package com.jiefutong.lehfu.utils;

import android.text.TextUtils;

/**
 * author：hj
 * time: 2018/1/20 0020 16:03
 * description:
 */


public class LoginUtils {

    public static boolean isLoginState() {
        String phonNumber = SharedPreferencesUtil.getPhoneNumber();
        return !(TextUtils.isEmpty(phonNumber));
    }
}
