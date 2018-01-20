package com.jiefutong.lehfu.utils;

import android.text.TextUtils;

/**
 * author：hj
 * time: 2018/1/20 0020 13:32
 * description:
 */


public class DataCheckUtils {

    public final static String REGEX_TELEPHONE = "^1\\d{10}$";

    public static boolean isTelPhone(String phone) {
        if (TextUtils.isEmpty(phone) || phone.length() != 11) {
            return false;
        }
        return phone.matches(REGEX_TELEPHONE);
    }
}
