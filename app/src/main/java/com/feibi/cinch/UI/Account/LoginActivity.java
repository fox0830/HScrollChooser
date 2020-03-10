package com.feibi.cinch.UI.Account;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.UI.Main.MainActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class LoginActivity extends BasicActivity implements View.OnClickListener {
    Banner banner;
    ArrayList<Drawable> images = new ArrayList<>();
    EditText et_account, et_password;
    TextView tv_merchant, tv_cinch;

    boolean isMerchant = true;

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
        findViewById(R.id.tv_forget_psw).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        et_account = findViewById(R.id.et_account);
        et_password = findViewById(R.id.et_password);
        refreshUI();
    }

    private void refreshUI() {
        tv_merchant.setBackground(isMerchant ? getDrawable(R.drawable.ic_check_btn) : null);
        tv_merchant.setTextColor(isMerchant ? getResources().getColor(R.color.white) : getResources().getColor(R.color.yellow));
        tv_cinch.setBackground(isMerchant ? null : getDrawable(R.drawable.ic_check_btn));
        tv_cinch.setTextColor(isMerchant ? getResources().getColor(R.color.yellow) : getResources().getColor(R.color.white));
    }

    @Override
    public void onClick(View view) {
  switch (view.getId()){
      case R.id.tv_merchant:
          isMerchant = true;
          refreshUI();
          break;
      case R.id.tv_cinch:
          isMerchant = false;
          refreshUI();
          break;
      case R.id.tv_login:
          break;
      case R.id.tv_forget_psw:
          break;
      case R.id.tv_register:
          startActivity(new Intent(this,RegisterActivity.class));
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
