package com.jiefutong.lehfu.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * author：hj
 * time: 2018/1/15 0015 00:15
 * description:
 */


public class AppUtil {

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号名称如：1.0
     */
    public static String getAppVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "无法获取当前版本";
        }
    }
}
