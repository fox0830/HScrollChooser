package com.feibi.cinch.UI.Account;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feibi.cinch.NetWork.module.NetWork;
import com.feibi.cinch.NetWork.request.CinchLoginReq;
import com.feibi.cinch.NetWork.respond.CinchData;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.UI.Main.MainActivity;
import com.feibi.cinch.utils.PreferencesUtil;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class LoginActivity extends BasicActivity implements View.OnClickListener {
    Banner banner;
    ArrayList<Drawable> images = new ArrayList<>();
    EditText et_account, et_password;
    TextView tv_merchant, tv_cinch, tv_forget_psw, tv_register;

    boolean isMerchant = true;
    NetWork netWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        banner = findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(6000);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });

        //设置数据
        images.add(getResources().getDrawable(R.mipmap.login_banner1));
        images.add(getResources().getDrawable(R.mipmap.login_banner2));
        banner.setImages(images);
        banner.start();

        tv_merchant = findViewById(R.id.tv_merchant);
        tv_merchant.setOnClickListener(this);
        tv_cinch = findViewById(R.id.tv_cinch);
        tv_cinch.setOnClickListener(this);
        findViewById(R.id.tv_term).setOnClickListener(this);
        findViewById(R.id.tv_login).setOnClickListener(this);
        tv_forget_psw = findViewById(R.id.tv_forget_psw);
        tv_forget_psw.setOnClickListener(this);
        tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(this);
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        netWork = new NetWork(this);
        refreshUI();

    }

    private void refreshUI() {
        tv_merchant.setBackground(isMerchant ? getDrawable(R.drawable.ic_check_btn) : null);
        tv_merchant.setTextColor(isMerchant ? getResources().getColor(R.color.white) : getResources().getColor(R.color.yellow));
        tv_cinch.setBackground(isMerchant ? null : getDrawable(R.drawable.ic_check_btn));
        tv_cinch.setTextColor(isMerchant ? getResources().getColor(R.color.yellow) : getResources().getColor(R.color.white));
        tv_forget_psw.setVisibility(!isMerchant ? View.VISIBLE : View.GONE);
        tv_register.setVisibility(!isMerchant ? View.VISIBLE : View.GONE);
        et_account.setHint(isMerchant?getString(R.string.hint_merchant):getString(R.string.hint_cinch));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_merchant:
                isMerchant = true;
                refreshUI();
                break;
            case R.id.tv_cinch:
                isMerchant = false;
                refreshUI();
                break;
            case R.id.tv_login:
                if (isMerchant) {

                } else {
                    showLoading();
                    netWork.cinchLogin(new CinchLoginReq(new CinchLoginReq.FormData(et_account.getText().toString(), et_password.getText().toString())), new ReqCallBack<BasicResponseBody<CinchData>>() {
                        @Override
                        public void onReqSuccess(BasicResponseBody<CinchData> result) {
                            PreferencesUtil.saveCinchData(LoginActivity.this, new Gson().toJson(result.getData()));
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                            dismissLoading();
                        }

                        @Override
                        public void onReqFailed(BasicResponseBody result) {
                            showToast(result.getMsg());
                            dismissLoading();
                        }
                    });
                }

                break;
            case R.id.tv_forget_psw:
                startActivity(new Intent(this, ForgetPwdActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.tv_term:
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
