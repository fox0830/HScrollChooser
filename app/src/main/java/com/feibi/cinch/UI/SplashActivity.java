package com.feibi.cinch.UI;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.feibi.cinch.R;
import com.feibi.cinch.UI.Account.LoginActivity;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.UI.Main.MainActivity;
import com.feibi.cinch.utils.Global;


public class SplashActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);
        jump();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {

    }

    private void jump() {
        if (isMb()) {
            if (!TextUtils.isEmpty(Global.MbNo)) {
                startActivity(new Intent(this, MainActivity.class));
                return;
            }
        } else if (Global.CINCH.equals(Global.useType)) {
            if (Global.cinchData != null && Global.cinchData.getLc_id() != null) {
                startActivity(new Intent(this, MainActivity.class));
                return;
            }
        }
        startActivity(new Intent(this, LoginActivity.class));
    }
}
