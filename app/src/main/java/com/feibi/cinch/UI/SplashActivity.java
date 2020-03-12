package com.feibi.cinch.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.feibi.cinch.NetWork.respond.CinchData;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Account.LoginActivity;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.UI.Main.MainActivity;
import com.feibi.cinch.utils.Global;
import com.feibi.cinch.utils.GsonUtil;
import com.feibi.cinch.utils.PreferencesUtil;


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
        Global.useType = PreferencesUtil.getUseType(this);
        if (Global.MERCHANT.equals(Global.useType)) {
            Global.MbNo = PreferencesUtil.getMbNo(this);
            if (!TextUtils.isEmpty(Global.MbNo)) {
                startActivity(new Intent(this, MainActivity.class));
                return;
            }
        } else if (Global.CINCH.equals(Global.useType)) {
            try {
                String jsonStr = PreferencesUtil.getCinchData(this);
                Global.cinchData = (CinchData) GsonUtil.str2Obj(jsonStr, CinchData.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (Global.cinchData != null && Global.cinchData.getLc_id() != null) {
                startActivity(new Intent(this, MainActivity.class));
                return;
            }
        }
        startActivity(new Intent(this, LoginActivity.class));

    }


}
