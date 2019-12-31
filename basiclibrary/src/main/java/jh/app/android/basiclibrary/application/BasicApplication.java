package jh.app.android.basiclibrary.application;

/**
 * Application类
 * Created by baorui on 2017-04-14.
 */

import android.app.Application;
import android.support.multidex.MultiDexApplication;

public class BasicApplication extends MultiDexApplication {

    private String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化异常捕获Handler
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext(), this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        System.exit(0);
    }
}