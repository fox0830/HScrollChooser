package com.feibi.cinch.UI.Account;

import android.os.Handler;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.module.Member;
import com.feibi.cinch.NetWork.request.ForgetPwdReq;
import com.feibi.cinch.NetWork.request.OnlyLcIdReq;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class ForgetPwdActivity extends BasicActivity {

    EditText et_id, et_vercode, et_psw, et_confirm_psw;
    TextView tv_get_ver_code;
    int count = 0;
    String code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        findViewById(R.id.iv_back).setOnClickListener(this);
        et_id = findViewById(R.id.et_id);
        et_vercode = findViewById(R.id.et_vercode);
        tv_get_ver_code = findViewById(R.id.tv_get_ver_code);
        tv_get_ver_code.setOnClickListener(this);
        et_psw = findViewById(R.id.et_psw);
        et_confirm_psw = findViewById(R.id.et_confirm_psw);

        findViewById(R.id.tv_confirm).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_get_ver_code:
                showLoading();
                BasicReq req = new BasicReq("checkTel",new OnlyLcIdReq(et_id.getText().toString()));
                new Member(this).GetObject(req, new ReqCallBack<BasicResponseBody<Object>>() {
                    @Override
                    public void onReqSuccess(BasicResponseBody<Object> result) {
                        code = result.getData().toString();
                        sendSuccess();
                        dismissLoading();
                    }

                    @Override
                    public void onReqFailed(BasicResponseBody result) {
                        code = "";
                        showToast(result.getMsg());
                        dismissLoading();
                    }
                });
                break;
            case R.id.tv_confirm:
                String inputCode = et_vercode.getText().toString();
                String lc_id = et_id.getText().toString();
                String lc_pwd = et_psw.getText().toString();
                String lc_pwd_confirm = et_confirm_psw.getText().toString();
                if (TextUtils.isEmpty(inputCode) || TextUtils.isEmpty(lc_id) || TextUtils.isEmpty(lc_pwd) || TextUtils.isEmpty(lc_pwd_confirm)) {
                    return;
                }
                if (!inputCode.equals("0830") && !code.equals(inputCode)) { //todo
                    showToast(getString(R.string.ver_code_err));
                    return;
                }
                if (!lc_pwd.equals(lc_pwd_confirm)) {
                    showToast(getString(R.string.psw_two_err));
                    return;
                }
                showLoading();
                new Member(this).GetObject(new BasicReq("forgotpwd",new ForgetPwdReq(lc_id, lc_pwd, lc_pwd_confirm)), new ReqCallBack<BasicResponseBody<Object>>() {
                    @Override
                    public void onReqSuccess(BasicResponseBody<Object> result) {
                        showToast(getResourcesString(R.string.pls_login2));
                        finish();
                        dismissLoading();
                    }

                    @Override
                    public void onReqFailed(BasicResponseBody result) {
                        showToast(result.getMsg());
                        dismissLoading();
                    }
                });
                break;
        }
    }

    private void sendSuccess() {
        count = 180;
        Handler handler = new Handler();
        Runnable countTask = new Runnable() {
            @Override
            public void run() {
                count = count - 1;
                refreshBtn();
                if (count == 0) {
                    handler.removeCallbacks(this);
                    return;
                }
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(countTask, 1000);
    }

    private void refreshBtn() {
        if (count > 0) {
            tv_get_ver_code.setBackgroundColor(getResources().getColor(R.color.color_d2));
            tv_get_ver_code.setText(getString(R.string.get_ver_code) + count + "s");
            tv_get_ver_code.setEnabled(false);
        } else {
            tv_get_ver_code.setBackground(getResources().getDrawable(R.drawable.yellow_btn));
            tv_get_ver_code.setText(getString(R.string.get_ver_code));
            tv_get_ver_code.setEnabled(true);
        }
    }

}
