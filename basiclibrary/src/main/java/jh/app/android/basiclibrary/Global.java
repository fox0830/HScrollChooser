package jh.app.android.basiclibrary;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * 全局类
 * Created by br on 2017/2/28 0028.
 */

public class Global {
    public boolean isDebug(Context context) {
        boolean isDebug = context.getApplicationInfo() != null &&
                (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        return isDebug;
    }

    public static String facebook_feedback_url = "https://www.facebook.com/imiroo/";
    public static String facebook_feedback_id = "fb://page/253796818127432";
}

