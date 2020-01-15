package com.feibi.trade.utils;


import android.content.Context;

import jh.app.android.basiclibrary.utils.PreferencesUtils;

public class PreferencesUtil extends PreferencesUtils {

    private static String TRIPINFO = "tripInfo";

    public static boolean saveTripInfo(Context context, String val) {
        return putString(context, TRIPINFO, val);
    }

    public static String getTripInfo(Context context) {
        return getString(context, TRIPINFO);
    }


}
