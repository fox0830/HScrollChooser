package jh.app.android.basiclibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统相关工具类
 * Created by br on 2017/2/23 0023.
 */

public class SyatemUtils {

    /**
     * 获取屏幕宽高
     *
     * @param context 上下文实例
     * @return Map<String, Integer> width,height
     */
    public static Map<String, Integer> getScreenWH(Context context) {
        Map<String, Integer> map = new HashMap<>();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        map.put("width", display.getWidth());
        map.put("height", display.getHeight());
        return map;
    }

    public static int getStatusBarHeight(Context context) {
        /**
         * 获取状态栏高度
         * */
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
//            versioncode = pi.versionCode;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (Exception e) {
//            Log.e("VersionInfo", "Exception", e);
            versionName = "";
        }
        return versionName;
    }
}
