package com.feibi.cinch.utils;


import android.content.Context;

import jh.app.android.basiclibrary.utils.PreferencesUtils;

public class PreferencesUtil extends PreferencesUtils {

    private static String lc_id = "lc_id";
    private static String cinchData = "CinchData";


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
