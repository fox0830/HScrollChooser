package com.feibi.trade.utils;


import android.content.Context;

import jh.app.android.basiclibrary.utils.PreferencesUtils;

public class PreferencesUtil extends PreferencesUtils {

    /**
     * 登陆用户的token值
     */
    private static String USER_TOKEN = "user_token";
    /**
     * 首次打開app
     */
    private static String HAS_OPENED = "has_opened";

    public static void logout(Context context) {
        removePreferences(context, USER_TOKEN);
    }

    public static boolean saveUserToken(Context context, String val) {
        return putString(context, USER_TOKEN, val);
    }

    public static String getUserToken(Context context) {
        return getString(context, USER_TOKEN);
    }

    public static boolean setHasOpened(Context context) {
        return putBoolean(context, HAS_OPENED, true);
    }

    public static Boolean isHasOpened(Context context) {
        return getBoolean(context, HAS_OPENED);
    }

}
