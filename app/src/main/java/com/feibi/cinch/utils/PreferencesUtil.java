package com.feibi.cinch.utils;


import android.content.Context;

import jh.app.android.basiclibrary.utils.PreferencesUtils;

public class PreferencesUtil extends PreferencesUtils {

    private static String isMerchant = "isMerchant";
    private static String lc_id = "lc_id";
    private static String cinchData = "CinchData";


    public static boolean saveIsMerchant(Context context, Boolean val) {
        return putBoolean(context, isMerchant, val);
    }

    public static boolean getIsMerchant(Context context) {
        return getBoolean(context, isMerchant);
    }


    public static boolean saveLcId(Context context, String val) {
        return putString(context, lc_id, val);
    }

    public static String getLcId(Context context) {
        return getString(context, lc_id);
    }

    public static boolean saveCinchData(Context context, String val) {
        return putString(context, cinchData, val);
    }

    public static String getCinchData(Context context) {
        return getString(context, cinchData);
    }


}
