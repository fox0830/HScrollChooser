package jh.app.android.basiclibrary.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * 权限获取类
 * Created by baorui on 2017/7/24.
 */

public class PermissionsGetter {

    public static boolean checkSelfPermissions(Activity activity, String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, 7501);
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPermissions(Activity activity, String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void reqPermissions(Activity activity, String[] permissions) {
        ActivityCompat.requestPermissions(activity, permissions, 7501);
    }
}
