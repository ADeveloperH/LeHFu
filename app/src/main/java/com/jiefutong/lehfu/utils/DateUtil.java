package com.jiefutong.lehfu.utils;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author：hj
 * time: 2018/1/15 0015 00:17
 * description:
 */


public class DateUtil {
    public static String formatStr_yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
    public static String formatStr_yyyyMMddHHmm = "yyyy-MM-dd HH:mm";
    public static String formatStr_yyyyMMddHH = "yyyy-MM-dd HH";
    public static String formatStr_yyyyMMdd = "yyyyMMdd";

    public static String getCurrentDateTime(@NonNull String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }
}
