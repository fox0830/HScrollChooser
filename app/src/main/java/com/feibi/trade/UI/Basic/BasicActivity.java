package com.feibi.trade.UI.Basic;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.feibi.trade.R;
import com.feibi.trade.UI.View.LoadingDialog;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import jh.app.android.basiclibrary.application.AppManager;
import jh.app.android.basiclibrary.utils.ActivityUtils;

public abstract class BasicActivity extends AppCompatActivity implements View.OnClickListener {
    LoadingDialog loadingDialog;
    TextView tv_wait_tip;
    String tips="";
    @Subscribe
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
//      tintManager.setTintColor(Color.parseColor("#48b3eb"));
        tintManager.setStatusBarTintResource(androud.app.br.basiclibrary.R.color.transparent);
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
//        EventBus.getDefault().register(this);
        ActivityUtils.addActivity(this);
        setBlackTextStatusBar(true);
        initLoadingDialog();
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

    public void showToast(String msg, int duration) {
        Toast.makeText(this, msg, duration).show();
    }

    public void showToast(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showToast(msg, Toast.LENGTH_SHORT);
            }
        });
    }

//    protected void setSystemBarHeight() {
//        View systemBar = findViewById(R.id.system_bar);
//        if (systemBar != null) {
//            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) systemBar.getLayoutParams(); //取控件textView当前的布局参数
//            linearParams.height = SyatemUtils.getStatusBarHeight(this);//獲取系統狀態欄高度
//            systemBar.setLayoutParams(linearParams);////将系統狀態欄高度设置给View==》R.id.system_bar
//        }
//    }
//
//    protected void setNoSystemBar() {
//        View systemBar = findViewById(R.id.system_bar);
//        if (systemBar != null) {
//            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) systemBar.getLayoutParams(); //取控件textView当前的布局参数
//            linearParams.height = 1;
//            systemBar.setLayoutParams(linearParams);
//        }
//    }

    protected void setBlackTextStatusBar(boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (dark) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
    }
    protected void initLoadingDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null);
        loadingDialog = new LoadingDialog(this, view);
        loadingDialog.setCancelable(false);
        tv_wait_tip = view.findViewById(R.id.tv_wait_tip);
    }
    protected void showLoading(){
        showLoading("Loading...");
    }

    protected void showLoading(String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loadingDialog!=null){
                    loadingDialog.setWaitText(text);
                    tips = "";
                    loadingDialog.show();
                }
            }
        });
    }

    protected void dismissLoading(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loadingDialog!=null){
                    loadingDialog.dismiss();
                }
            }
        });
    }

    protected void setLoadingTxt(String txt){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loadingDialog!=null){
                    tv_wait_tip.setText(txt);
                }
            }
        });
    }
    protected void appendLoadingTxt(String txt){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(loadingDialog!=null){
                    tips+=txt;
                    tv_wait_tip.setText(tips);
                }
            }
        });
    }

//    protected void getShadowData(String thingId){
//        /*subscribe IoT Shadow /update/delta topic */
//        Intent intent = new Intent(this, AwsService.class);
//        Intent subscribe_intent = new Intent(this, AwsService.class);
//        subscribe_intent.putExtra(ServiceConstant.AWSThingName,thingId);
//        subscribe_intent.setAction(ServiceConstant.JSONMsgShadowGet);
//        startService(subscribe_intent);
//    }


}
