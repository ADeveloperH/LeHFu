package com.jiefutong.lehfu.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jiefutong.lehfu.MyApplication;

public class SharedPreferencesUtil {
    //记录项目中其他需要记录的字段
    public static final String Share_Preferences_Name_Private = "LeHFu_Private";

    public static final String Share_Preferences_Name_History = "LeHFu_Hisory";

    public static final String KEY_UPDATE_TIME = "KEY_UPDATE_TIME";

    public static final String KEY_APPTOKEN = "key_apptoken";


    private static Context mcontext;

    static {
        mcontext = MyApplication.getInstance();
    }

    /**
     * 该配置文件记录项目中其他需要记录的字段
     */
    public static SharedPreferences getPreferencesPrivate() {
        if (mcontext != null) {
            return mcontext.getSharedPreferences(Share_Preferences_Name_Private, Context.MODE_MULTI_PROCESS);
        }
        return null;
    }

    public static SharedPreferences getPreferencesHistory() {
        if (mcontext != null) {
            return mcontext.getSharedPreferences(Share_Preferences_Name_History, Context.MODE_MULTI_PROCESS);
        }
        return null;
    }
//===========================================getPreferencesPrivate	START====================================//

    public static boolean setAppToken(String appToken) {
        if (getPreferencesPrivate() != null) {
            return getPreferencesPrivate().edit().putString(KEY_APPTOKEN, appToken).commit();
        }
        return false;
    }

    public static String getAppToken() {
        if (getPreferencesPrivate() != null) {
            return getPreferencesPrivate().getString(KEY_APPTOKEN, "-1");
        }
        return "-1";
    }

//===========================================getPreferencesPrivate	END====================================//

//===========================================getPreferencesHistory	START====================================//

    public static boolean setUpdateTime(String version, String updateTime) {
        if (getPreferencesHistory() != null) {
            return getPreferencesHistory().edit().putString(KEY_UPDATE_TIME + version, updateTime).commit();
        }
        return false;
    }

    public static String getUpdateTime(String version) {
        if (getPreferencesHistory() != null) {
            return getPreferencesHistory().getString(KEY_UPDATE_TIME + version, "");
        }
        return "";
    }


//==============================getPreferencesHistory	END=========================================//

    /**
     * 清除保存的数据(不包括第一次登陆)
     */
    public static boolean clear() {
        return getPreferencesPrivate().edit()
                .clear().commit();
    }
}
