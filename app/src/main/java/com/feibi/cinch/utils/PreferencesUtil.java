package com.feibi.cinch.utils;


import android.content.Context;

import jh.app.android.basiclibrary.utils.PreferencesUtils;

public class PreferencesUtil extends PreferencesUtils {

    private static String TRIPINFO = "tripInfo";
    private static String uploadPicInfo = "uploadPicInfo";

    public static boolean saveTripInfo(Context context, String val) {
        return putString(context, TRIPINFO, val);
    }

    public static String getTripInfo(Context context) {
        return getString(context, TRIPINFO);
    }


    public static boolean saveUploadPicInfo(Context context, String val) {
        return putString(context, uploadPicInfo, val);
    }

    public static String getUploadPicInfo(Context context) {
        return getString(context, uploadPicInfo);
    }


}
