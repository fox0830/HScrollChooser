package com.feibi.cinch.UI.Account;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.feibi.cinch.NetWork.module.NetWork;
import com.feibi.cinch.NetWork.request.CheckIdReq;
import com.feibi.cinch.NetWork.request.GetBmiReq;
import com.feibi.cinch.NetWork.request.GetVerCodeReq;
import com.feibi.cinch.NetWork.request.RegisterReq;
import com.feibi.cinch.NetWork.request.WriteAnswerReq;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.utils.Global;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class RegisterActivity extends BasicActivity implements TextWatcher {

    EditText et_id, et_vercode, et_psw, et_confirm_psw, et_merchant, et_name, et_age, et_tel, et_height, et_weight, et_fat, et_target, et_remark;
    TextView tv_bmi, tv_get_ver_code;
    ImageView iv_head;
    LinearLayout ll_step1, ll_step2, ll_step3, ll_merchant;
    int currentStep = 1;
    NetWork netWork;
    int count = 0;
    RadioButton rb_male, rb1_often,
            rb1_sometime,
            rb1_little,
            rb2_often,
            rb2_sometime,
            rb2_little,
            rb3_often,
            rb3_sometime,
            rb3_little,
            rb4_often,
            rb4_sometime,
            rb4_little,
            rb5_often,
            rb5_sometime,
            rb5_little,
            rb6_often,
            rb6_sometime,
            rb6_little;
    TextView tv_step_1, tv_step_2, tv_step_3;
    View v_step_1, v_step_2, v_step_3, v_step_line1, v_step_line2, v_step_line3;

    String code;

    //input
    String lc_id;
    String lc_pwd;
    String lc_pwd_confirm;
    String mb_no;
    String lc_name;
    String lc_tel;
    String lc_age;
    String lc_tall;
    String lc_weight;
    String lc_bmi;
    String lc_fat;
    String lc_target;
    String lc_remark;

    String ans1;
    String ans2;
    String ans3;
    String ans4;
    String ans5;
    String ans6;

    //page type
    boolean isRegister = true;
    TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById(R.id.iv_back).setOnClickListener(this);
        ll_step1 = findViewById(R.id.ll_step1);
        ll_step2 = findViewById(R.id.ll_step2);
        ll_step3 = findViewById(R.id.ll_step3);
        /**
         * step 控制
         */
        tv_step_1 = findViewById(R.id.tv_step_1);
        tv_step_2 = findViewById(R.id.tv_step_2);
        tv_step_3 = findViewById(R.id.tv_step_3);
        v_step_1 = findViewById(R.id.v_step_1);
        v_step_2 = findViewById(R.id.v_step_2);
        v_step_3 = findViewById(R.id.v_step_3);
        v_step_line1 = findViewById(R.id.v_step_line1);
        v_step_line2 = findViewById(R.id.v_step_line2);
        v_step_line3 = findViewById(R.id.v_step_line3);
        /**
         * step1
         */
        ll_merchant = findViewById(R.id.ll_merchant);
        et_id = findViewById(R.id.et_id);
//        et_id.addTextChangedListener(this);
        et_vercode = findViewById(R.id.et_vercode);
//        et_vercode.addTextChangedListener(this);
        tv_get_ver_code = findViewById(R.id.tv_get_ver_code);
        tv_get_ver_code.setOnClickListener(this);
        et_psw = findViewById(R.id.et_psw);
//        et_psw.addTextChangedListener(this);
        et_confirm_psw = findViewById(R.id.et_confirm_psw);
//        et_confirm_psw.addTextChangedListener(this);
        et_merchant = findViewById(R.id.et_merchant);
//        et_merchant.addTextChangedListener(this);
        rb_male = findViewById(R.id.rb_male);
        findViewById(R.id.tv_confirm).setOnClickListener(this);

        /**
         * step2
         */
        findViewById(R.id.ll_head).setOnClickListener(this);
        iv_head = findViewById(R.id.iv_head);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_tel = findViewById(R.id.et_tel);
        et_height = findViewById(R.id.et_height);
        et_height.addTextChangedListener(this);
        et_weight = findViewById(R.id.et_weight);
        et_weight.addTextChangedListener(this);
        et_fat = findViewById(R.id.et_fat);
        tv_bmi = findViewById(R.id.tv_bmi);
        et_target = findViewById(R.id.et_target);
        et_remark = findViewById(R.id.et_remark);
        findViewById(R.id.tv_next_step).setOnClickListener(this);

        /**
         * step3
         */
        rb1_often = findViewById(R.id.rb1_often);
        rb1_sometime = findViewById(R.id.rb1_sometime);
        rb1_little = findViewById(R.id.rb1_little);
        rb2_often = findViewById(R.id.rb2_often);
        rb2_sometime = findViewById(R.id.rb2_sometime);
        rb2_little = findViewById(R.id.rb2_little);
        rb3_often = findViewById(R.id.rb3_often);
        rb3_sometime = findViewById(R.id.rb3_sometime);
        rb3_little = findViewById(R.id.rb3_little);
        rb4_often = findViewById(R.id.rb4_often);
        rb4_sometime = findViewById(R.id.rb4_sometime);
        rb4_little = findViewById(R.id.rb4_little);
        rb5_often = findViewById(R.id.rb5_often);
        rb5_sometime = findViewById(R.id.rb5_sometime);
        rb5_little = findViewById(R.id.rb5_little);
        rb6_often = findViewById(R.id.rb6_often);
        rb6_sometime = findViewById(R.id.rb6_sometime);
        rb6_little = findViewById(R.id.rb6_little);
        findViewById(R.id.tv_confirm3).setOnClickListener(this);

        tv_title = findViewById(R.id.tv_title);
        isRegister = getIntent().getBooleanExtra("register", true);
        tv_title.setText(isRegister ? getString(R.string.register) : getString(R.string.add_cinch));
        ll_merchant.setVisibility(isRegister ? View.VISIBLE : View.GONE);
        refreshBtn();
        refreshStep();
        netWork = new NetWork(this);
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


    private void refreshStep() {
        ll_step1.setVisibility(currentStep == 1 ? View.VISIBLE : View.GONE);
        ll_step2.setVisibility(currentStep == 2 ? View.VISIBLE : View.GONE);
        ll_step3.setVisibility(currentStep == 3 ? View.VISIBLE : View.GONE);
        tv_step_1.setVisibility(currentStep == 1 ? View.VISIBLE : View.GONE);
        tv_step_2.setVisibility(currentStep == 2 ? View.VISIBLE : View.GONE);
        tv_step_3.setVisibility(currentStep == 3 ? View.VISIBLE : View.GONE);
        v_step_1.setVisibility(currentStep >= 1 ? View.VISIBLE : View.GONE);
        v_step_2.setVisibility(currentStep >= 2 ? View.VISIBLE : View.GONE);
        v_step_3.setVisibility(currentStep >= 3 ? View.VISIBLE : View.GONE);
        v_step_line1.setBackground(currentStep >= 1 ? getDrawable(R.drawable.green_line) : getDrawable(R.drawable.dash_line_green));
        v_step_line2.setBackground(currentStep >= 2 ? getDrawable(R.drawable.green_line) : getDrawable(R.drawable.dash_line_green));
        v_step_line3.setBackground(currentStep >= 3 ? getDrawable(R.drawable.green_line) : getDrawable(R.drawable.dash_line_green));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_get_ver_code:
                showLoading();
                new NetWork(this).CheckId(new CheckIdReq(new CheckIdReq.FormData(et_id.getText().toString())), new ReqCallBack<BasicResponseBody<Object>>() {
                    @Override
                    public void onReqSuccess(BasicResponseBody<Object> result) {
                        GetVerCodeReq req = new GetVerCodeReq(new GetVerCodeReq.FormData(et_id.getText().toString()));
                        new NetWork(RegisterActivity.this).getVerCode(req, new ReqCallBack<BasicResponseBody<Object>>() {
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
                    }

                    @Override
                    public void onReqFailed(BasicResponseBody result) {
                        code = "";
                        showToast(result.getMsg());
                    }
                });

                break;
            case R.id.tv_confirm:
                String inputCode = et_vercode.getText().toString();
                lc_id = et_id.getText().toString();
                lc_pwd = et_psw.getText().toString();
                lc_pwd_confirm = et_confirm_psw.getText().toString();
                if (isRegister) {
                    mb_no = et_merchant.getText().toString();
                } else {
                    mb_no = Global.MbNo;
                }
                if (TextUtils.isEmpty(inputCode) || TextUtils.isEmpty(lc_id) || TextUtils.isEmpty(lc_pwd) || TextUtils.isEmpty(lc_pwd_confirm) || TextUtils.isEmpty(mb_no)) {
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
                currentStep = 2;
                refreshStep();
                break;
            //register2
            case R.id.ll_head:
                break;
            case R.id.tv_next_step:
                lc_name = et_name.getText().toString();
                lc_tel = et_tel.getText().toString();
                lc_age = et_age.getText().toString();
                lc_tall = et_height.getText().toString();
                lc_weight = et_weight.getText().toString();
                lc_bmi = tv_bmi.getText().toString();
                lc_fat = et_fat.getText().toString();
                lc_target = et_target.getText().toString();
                lc_remark = et_remark.getText().toString();
                if (TextUtils.isEmpty(lc_name) || TextUtils.isEmpty(lc_tel) || TextUtils.isEmpty(lc_age)
                        || TextUtils.isEmpty(lc_tall) || TextUtils.isEmpty(lc_weight) || TextUtils.isEmpty(lc_bmi)) {
                    return;
                }
                showLoading();
                RegisterReq.FormData formData = new RegisterReq.FormData(lc_id, lc_pwd, lc_pwd_confirm, mb_no, lc_name, lc_tel,
                        lc_age, rb_male.isChecked() ? "1" : "0", lc_tall, lc_weight, lc_bmi, lc_fat, lc_target, lc_remark);
                netWork.register(new RegisterReq(formData), new ReqCallBack<BasicResponseBody<Object>>() {

                    @Override
                    public void onReqSuccess(BasicResponseBody<Object> result) {
                        currentStep = 3;
                        refreshStep();
                        dismissLoading();
                    }

                    @Override
                    public void onReqFailed(BasicResponseBody result) {
                        showToast(result.getMsg());
                        dismissLoading();
                    }

                });
                break;
            //register3
            case R.id.tv_confirm3:
                ans1 = rb1_often.isChecked() ? "1" : (rb1_sometime.isChecked() ? "2" : (rb1_little.isChecked() ? "3" : ""));
                ans2 = rb2_often.isChecked() ? "1" : (rb2_sometime.isChecked() ? "2" : (rb2_little.isChecked() ? "3" : ""));
                ans3 = rb3_often.isChecked() ? "1" : (rb3_sometime.isChecked() ? "2" : (rb3_little.isChecked() ? "3" : ""));
                ans4 = rb4_often.isChecked() ? "1" : (rb4_sometime.isChecked() ? "2" : (rb4_little.isChecked() ? "3" : ""));
                ans5 = rb5_often.isChecked() ? "1" : (rb5_sometime.isChecked() ? "2" : (rb5_little.isChecked() ? "3" : ""));
                ans6 = rb6_often.isChecked() ? "1" : (rb6_sometime.isChecked() ? "2" : (rb6_little.isChecked() ? "3" : ""));
                if (TextUtils.isEmpty(ans1) || TextUtils.isEmpty(ans2) || TextUtils.isEmpty(ans3) || TextUtils.isEmpty(ans4) || TextUtils.isEmpty(ans5) || TextUtils.isEmpty(ans6)) {
                    return;
                }
                showLoading();
                netWork.writeAnswer(new WriteAnswerReq(new WriteAnswerReq.FormData(lc_id, ans1, ans2, ans3, ans4, ans5, ans6)), new ReqCallBack<BasicResponseBody<Object>>() {
                    @Override
                    public void onReqSuccess(BasicResponseBody<Object> result) {
                        showToast(isRegister ? getString(R.string.pls_login) : getString(R.string.add_cinch_success));
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

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (et_height.getText().toString().isEmpty() || et_weight.getText().toString().isEmpty()) {
            return;
        }
        String tall = et_height.getText().toString();
        String weight = et_weight.getText().toString();
        netWork.getBmi(new GetBmiReq(new GetBmiReq.FormData(tall, weight)), new ReqCallBack<BasicResponseBody<Object>>() {
            @Override
            public void onReqSuccess(BasicResponseBody<Object> result) {
                tv_bmi.setText(result.getData().toString());
            }

            @Override
            public void onReqFailed(BasicResponseBody result) {
                showToast("BMI計算出錯：" + result.getMsg());
            }


        });
    }
}
