package com.feibi.cinch.UI.Account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.feibi.cinch.NetWork.module.NetWork;
import com.feibi.cinch.NetWork.request.ChangePwdReq;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.utils.Global;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class ChangePwdActivity extends BasicActivity {
    EditText et_old_psw, et_psw, et_confirm_psw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.tv_confirm).setOnClickListener(this);
        et_old_psw = findViewById(R.id.et_old_psw);
        et_psw = findViewById(R.id.et_psw);
        et_confirm_psw = findViewById(R.id.et_confirm_psw);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_confirm:
                String old_pwd = et_old_psw.getText().toString();
                String pwd = et_psw.getText().toString();
                String confirm_pwd = et_confirm_psw.getText().toString();
                if (TextUtils.isEmpty(old_pwd) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(confirm_pwd)) {
                    return;
                }
                showLoading();
                new NetWork(this).ChangePwd(new ChangePwdReq(new ChangePwdReq.FormData(Global.cinchData.getLc_id(), old_pwd, pwd, confirm_pwd)), new ReqCallBack<BasicResponseBody<Object>>() {
                    @Override
                    public void onReqSuccess(BasicResponseBody<Object> result) {
                        dismissLoading();
                        showToast(getString(R.string.change_pwd_success));
                        finish();
                    }

                    @Override
                    public void onReqFailed(BasicResponseBody result) {
                        dismissLoading();
                        showToast(result.getMsg());
                    }
                });
                break;
        }
    }
}
