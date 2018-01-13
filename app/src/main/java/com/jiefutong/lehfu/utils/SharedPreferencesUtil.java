package com.jiefutong.lehfu.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.jiefutong.lehfu.MyApplication;

public class SharedPreferencesUtil {
    //记录项目中其他需要记录的字段
    public static final String Share_Preferences_Name_Private = "4GManger_Private";

    public static final String POINT = "point"; //剩余积分

    public static final String MONEY = "money"; //剩余余额

    public static final String TOKEN = "token"; //登录成功后后台会传过来一个token，这里保存起来，掉其他接口时需要

    public static final String ZONEID = "zoneid";

    public static final String ISPAYPWDSET = "ispaypwdset"; //是否设置过支付密码

    public static final String KEY_ACCOUNT = "KEY_ACCOUNT";//手机号

    public static final String KEY_USERID = "KEY_USERID";//userid
    public static final String KEY_NICKNAME = "KEY_NICKNAME";//nickname
    public static final String KEY_USERICON = "KEY_USERICON";//usericonurl
    public static final String KEY_DOWNLODEMSG = "downlodemsg"; //已下载安装包的信息

    public static final String KEY_HOME_TYPE_LIST = "hometypelist"; //商品类别信息

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


//===========================================getPreferencesLogin	START====================================//




//==============================getPreferencesLogin	END=========================================//

    /**
     * 清除保存的数据(不包括第一次登陆)
     */
    public static boolean clear() {
        return getPreferencesPrivate().edit()
                .clear().commit();
    }
}
