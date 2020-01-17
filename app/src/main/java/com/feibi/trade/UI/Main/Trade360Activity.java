package com.feibi.trade.UI.Main;

import android.annotation.SuppressLint;
import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.bumptech.glide.Glide;
import com.feibi.trade.NetWork.module.NetWork;
import com.feibi.trade.NetWork.respond.GetTradeRes;
import com.feibi.trade.NetWork.respond.UploadInfoRes;
import com.feibi.trade.R;
import com.feibi.trade.UI.Basic.BasicActivity;
import com.feibi.trade.UI.View.MyDialog;
import com.feibi.trade.utils.Global;
import com.feibi.trade.utils.PreferencesUtil;
import com.google.gson.Gson;

import jh.app.android.basiclibrary.network.ReqCallBack;


public class Trade360Activity extends BasicActivity {
    WebView webview;

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade360);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_del).setOnClickListener(this);
        webview = findViewById(R.id.webview);
        webview.setInitialScale(100);
        webview.addJavascriptInterface(this, "native");
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
//        webSettings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
//        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        webSettings.setSavePassword(false);
        webSettings.setSaveFormData(false);
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片

        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webview.loadUrl("http://61.222.197.34:10093/Map/trip/" + Global.TripInfo.getId());
//        webview.loadUrl("https://www.google.com/intl/zh-CN/streetview/");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_del:
                webview.evaluateJavascript("javascript:get_vue_obj()", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        //此处为 js 返回的结果
                        new MyDialog(Trade360Activity.this).builder().setIcon(getDrawable(R.mipmap.red_delete)).setTitle("DELETE")
                                .setDoneButton(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        delSpot(Integer.valueOf(value));
                                    }
                                })
                                .show();
                    }
                });

                break;
        }
    }

    private void delSpot(int index) {
        int finalIndex = index-1;
        new NetWork(this).delSpot("trip/basic/" + Global.TripInfo.getId() + "/spot/" + finalIndex + "/" + Global.TripInfo.getToken(), new ReqCallBack<UploadInfoRes>() {
                    @Override
                    public void onReqSuccess(UploadInfoRes result) {
                        showToast("刪除成功");
                        if(Global.hasUpload.getHasUploadPic().size()> finalIndex){
                            Global.hasUpload.remove(finalIndex);
                            String jsonString = new Gson().toJson(Global.hasUpload);
                            PreferencesUtil.saveUploadPicInfo(Trade360Activity.this, jsonString);
                        }
                        webview.reload();
                    }

                    @Override
                    public void onReqFailed(Object result) {
                        showToast("刪除失敗");
                    }
                }
        );
    }
}
