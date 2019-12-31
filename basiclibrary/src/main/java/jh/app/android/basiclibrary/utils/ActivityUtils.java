package jh.app.android.basiclibrary.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtils {

        public static List<Activity> activities = new ArrayList<>();

        public static void addActivity(Activity activity){
            activities.add(activity);
        }

        public static void removeActivity(Activity activity){
            activities.remove(activity);
        }

        public static void finishAll(){
            for (Activity activity:activities){
                if (!activity.isFinishing()){
                    activity.finish();
                }
            }
        }
    public static Intent getAppOpenIntentByPackageName(Context context, String packageName){
        String mainAct = null;
        PackageManager pkgMag = context.getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED|Intent.FLAG_ACTIVITY_NEW_TASK);

        @SuppressLint("WrongConstant") List<ResolveInfo> list = pkgMag.queryIntentActivities(intent,
                PackageManager.GET_ACTIVITIES);
        for (int i = 0; i < list.size(); i++) {
            ResolveInfo info = list.get(i);
            if (info.activityInfo.packageName.equals(packageName)) {
                mainAct = info.activityInfo.name;
                break;
            }
        }
        if (TextUtils.isEmpty(mainAct)) {
            return null;
        }
        intent.setComponent(new ComponentName(packageName, mainAct));
        return intent;
    }

}
