package com.feibi.trade.UI;

import android.os.Bundle;
import android.view.View;

import com.feibi.trade.R;
import com.feibi.trade.UI.Basic.BasicActivity;

public class SplashActivity extends BasicActivity {
    //    wifiSmartDeviceReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String regId = JPushInterface.getRegistrationID(this);
        setContentView(R.layout.activity_spalsh);
//        receiver = new wifiSmartDeviceReceiver();
        jump();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        IntentFilter filter = new IntentFilter(ServiceConstant.CloudStatus);
//        filter.addAction(ServiceConstant.JSONShadowMsgReport);
//        filter.addCategory(Intent.CATEGORY_DEFAULT);
//        registerReceiver(receiver, filter);
//        initManager();
    }

    @Override
    public void onClick(View v) {

    }

    private void jump() {

    }




}
