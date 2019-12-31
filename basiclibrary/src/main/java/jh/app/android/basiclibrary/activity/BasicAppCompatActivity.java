package jh.app.android.basiclibrary.activity;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.greenrobot.eventbus.EventBus;

import androud.app.br.basiclibrary.R;
import jh.app.android.basiclibrary.application.AppManager;
import jh.app.android.basiclibrary.utils.ActivityUtils;

/**
 * AppCompatActivity基类
 * Created by br on 2017/2/28 0028.
 */

public abstract class BasicAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppManager.getAppManager().addActivity(this);

        // 设置样式
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 4.4及以上版本开启
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);

        // 自定义颜色
//        tintManager.setTintColor(Color.parseColor("#48b3eb"));
        tintManager.setStatusBarTintResource(R.color.transparent);

        // 5.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        EventBus.getDefault().register(this);
        ActivityUtils.addActivity(this);
        beforeLayout();
        getIntentParams();
        initLayout();
        prepareData();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        AppManager.getAppManager().finishActivity(this);
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    protected String getResourcesString(int id) {
        return getResources().getString(id);
    }

    /**
     * 获取intent附加数据
     */
    protected abstract void getIntentParams();

    /**
     * 初始化布局
     */
    protected abstract void initLayout();

    /**
     * 初始化业务数据
     */
    protected abstract void prepareData();

    /**
     * 页面初始化前
     */
    protected abstract void beforeLayout();

}
