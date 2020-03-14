package com.fox.hscrollchooser.utils;


import android.content.Context;

import jh.app.android.basiclibrary.utils.PreferencesUtils;

public class PreferencesUtil extends PreferencesUtils {

    private static String useType = "useType";
    private static String mb_no= "mb_no";
    private static String cinchData = "CinchData";



    public static boolean saveUseType(Context context, String val) {
        return putString(context, useType, val);
    }

    public static String getUseType(Context context) {
        return getString(context, useType);
    }



    public static boolean saveMbNo(Context context, String val) {
        return putString(context, mb_no, val);
    }

    public static String getMbNo(Context context) {
        return getString(context, mb_no);
    }

    public static boolean saveCinchData(Context context, String val) {
        return putString(context, cinchData, val);
    }

    public static String getCinchData(Context context) {
        return getString(context, cinchData);
    }


}
