package com.feibi.cinch.UI.Main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feibi.cinch.NetWork.module.NetWork;
import com.feibi.cinch.NetWork.request.SloganReq;
import com.feibi.cinch.NetWork.respond.CinchData;
import com.feibi.cinch.NetWork.respond.SloganData;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Account.LoginActivity;
import com.feibi.cinch.UI.Account.PersonalDataActivity;
import com.feibi.cinch.UI.AddFriend.MyFriendActivity;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.UI.GroupThin.GroupThinActivity;
import com.feibi.cinch.UI.GroupThin.MbGroupThinActivity;
import com.feibi.cinch.utils.Global;
import com.feibi.cinch.utils.GsonUtil;
import com.feibi.cinch.utils.PreferencesUtil;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class MainActivity extends BasicActivity implements View.OnClickListener {

    Banner banner;
    ArrayList<Drawable> images = new ArrayList<>();

    TextView tv_slogan;
    ConstraintLayout cl_add_friend, cl_personal_data;
    LinearLayout ll_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        banner = findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片集合
        //设置banner动画效果
//        banner.setBannerAnimation(Transformer.DepthPage);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(6000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });

        //设置数据
        images.add(getResources().getDrawable(R.mipmap.main_top_bg));
        images.add(getResources().getDrawable(R.drawable.ic_main_top_bg2));
        banner.setImages(images);
        banner.start();

        ll_logout = findViewById(R.id.ll_logout);
        ll_logout.setOnClickListener(this);
        cl_add_friend = findViewById(R.id.cl_add_friend);
        cl_add_friend.setOnClickListener(this);
        cl_personal_data = findViewById(R.id.cl_personal_data);
        cl_personal_data.setOnClickListener(this);
        findViewById(R.id.cl_group_thin).setOnClickListener(this);
        findViewById(R.id.cl_love_share).setOnClickListener(this);

        cl_add_friend.setVisibility(Global.MERCHANT.equals(Global.useType) ? View.VISIBLE : View.GONE);
        cl_personal_data.setVisibility(Global.MERCHANT.equals(Global.useType) ? View.GONE : View.VISIBLE);
        ll_logout.setVisibility(Global.MERCHANT.equals(Global.useType) ? View.VISIBLE : View.GONE);

        tv_slogan = findViewById(R.id.tv_slogan);
        tv_slogan.setText(getString(R.string.slogan));
        getSlogan();
    }

    public void getSlogan() {
        new NetWork(this).getSlogan(new SloganReq(new SloganReq.FormData("1")), new ReqCallBack<BasicResponseBody<ArrayList>>() {
            @Override
            public void onReqSuccess(BasicResponseBody<ArrayList> result) {
                ArrayList<SloganData> slogans = new ArrayList<>();
                for (int i = 0; i < result.getData().size(); i++) {
                    String json = new Gson().toJson(result.getData().get(i));
                    try {
                        SloganData sloganData = (SloganData) GsonUtil.str2Obj(json, SloganData.class);
                        slogans.add(sloganData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (slogans.size() > 0) {
                    final int[] pos = {0};
                    Handler handler = new Handler();
                    Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            tv_slogan.setText(slogans.get(pos[0]).getSlogan());
                            pos[0] += 1;
                            if (pos[0] >= slogans.size()) {
                                pos[0] = 0;
                            }
                            handler.postDelayed(this, 5000);
                        }
                    };
                    handler.post(runnable);
                }
            }

            @Override
            public void onReqFailed(BasicResponseBody result) {

            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void checkPermission() {
        int targetSdkVersion = 0;
        String[] PermissionString = {Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        try {
            final PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            targetSdkVersion = info.applicationInfo.targetSdkVersion;//获取应用的Target版本
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
//            Log.e("err", "检查权限_err0");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Build.VERSION.SDK_INT是获取当前手机版本 Build.VERSION_CODES.M为6.0系统
            //如果系统>=6.0
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                //第 1 步: 检查是否有相应的权限
                boolean isAllGranted = checkPermissionAllGranted(PermissionString);
                if (isAllGranted) {
                    //Log.e("err","所有权限已经授权！");
                    return;
                }
                // 一次请求多个权限, 如果其他有权限是已经授予的将会自动忽略掉
                ActivityCompat.requestPermissions(this,
                        PermissionString, 1);
            }
        }
    }

    /**
     * 检查是否拥有指定的所有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                // 只要有一个权限没有被授予, 则直接返回 false
                //Log.e("err","权限"+permission+"没有授权");
                return false;
            }
        }
        return true;
    }

    //申请权限结果返回处理
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            boolean isAllGranted = true;
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }
            if (isAllGranted) {
                Log.e("err", "权限都授权了");
            } else {
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_logout:
                PreferencesUtil.saveUseType(this, "");
                PreferencesUtil.saveCinchData(this, "");
                Global.cinchData = new CinchData();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.cl_add_friend:
                startActivity(new Intent(this, MyFriendActivity.class));
                break;
            case R.id.cl_personal_data:
                startActivity(new Intent(this, PersonalDataActivity.class));
                break;
            case R.id.cl_group_thin:
                if(Global.MERCHANT.equals(Global.useType)){
                    startActivity(new Intent(this, MbGroupThinActivity.class));
                }else {
                    startActivity(new Intent(this, GroupThinActivity.class));
                }
                break;
            case R.id.cl_love_share:
                break;
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }

}
