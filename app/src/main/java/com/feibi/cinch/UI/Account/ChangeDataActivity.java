package com.feibi.cinch.UI.Account;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.feibi.cinch.NetWork.module.NetWork;
import com.feibi.cinch.NetWork.request.ChangeDataReq;
import com.feibi.cinch.NetWork.request.UploadRecordReq;
import com.feibi.cinch.NetWork.respond.CinchData;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.utils.ChoosePictureUtils;
import com.feibi.cinch.utils.Global;
import com.feibi.cinch.utils.ImageTool;
import com.feibi.cinch.utils.PreferencesUtil;

import java.io.File;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

import static com.feibi.cinch.utils.ChoosePictureUtils.ALBUM;
import static com.feibi.cinch.utils.ChoosePictureUtils.CAMERA;
import static com.feibi.cinch.utils.ChoosePictureUtils.CUT_PHOTO;
import static jh.app.android.basiclibrary.Constants.MAIN_PATH;
import static jh.app.android.basiclibrary.Constants.getPhotoName;

public class ChangeDataActivity extends BasicActivity {

    ImageView iv_head;
    EditText et_name, et_age, et_tel, et_target, et_remark;
    RadioButton rb_male, rb_female;
    TextView tv_height, tv_weight, tv_fat, tv_bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_data);
        findViewById(R.id.ll_head).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.ll_data1).setOnClickListener(this);
        findViewById(R.id.ll_data2).setOnClickListener(this);
        findViewById(R.id.tv_save).setOnClickListener(this);
        iv_head = findViewById(R.id.iv_head);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_tel = findViewById(R.id.et_tel);

        rb_male = findViewById(R.id.rb_male);
        rb_female = findViewById(R.id.rb_female);

        tv_height = findViewById(R.id.tv_height);
        tv_weight = findViewById(R.id.tv_weight);
        tv_fat = findViewById(R.id.tv_fat);
        tv_bmi = findViewById(R.id.tv_bmi);

        et_target = findViewById(R.id.et_target);
        et_remark = findViewById(R.id.et_remark);

        et_name.setText(Global.cinchData.getLc_name());
        et_age.setText(Global.cinchData.getLc_age());
        et_tel.setText(Global.cinchData.getLc_tel());
        tv_height.setText(Global.cinchData.getLc_tall());
        tv_weight.setText(Global.cinchData.getLc_weight());
        tv_fat.setText(Global.cinchData.getLc_fat());
        tv_bmi.setText(Global.cinchData.getLc_bmi());
        et_target.setText(Global.cinchData.getLc_target());
        et_remark.setText(Global.cinchData.getLc_remark());
        rb_male.setChecked(Global.cinchData.getLc_sex().equals("1"));
        rb_female.setChecked(Global.cinchData.getLc_sex().equals("0"));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_head:
                startActivity(new Intent(this, SeeHeadActivity.class));
                break;
            case R.id.ll_data1:
            case R.id.ll_data2:
//                startActivity(new Intent(this, ChangePwdActivity.class));
                break;
            case R.id.tv_save:
                String lc_name = et_name.getText().toString();
                String lc_tel = et_tel.getText().toString();
                String lc_age = et_age.getText().toString();
                String lc_sex = rb_male.isChecked() ? "1" : "0";
                String lc_tall = tv_height.getText().toString();
                String lc_weight = tv_weight.getText().toString();
                String lc_bmi = tv_bmi.getText().toString();
                String lc_fat = tv_fat.getText().toString();
                String lc_target = et_target.getText().toString();
                String lc_remark = et_remark.getText().toString();
                if (TextUtils.isEmpty(lc_name) || TextUtils.isEmpty(lc_tel) || TextUtils.isEmpty(lc_age) || TextUtils.isEmpty(lc_sex)
                        || TextUtils.isEmpty(lc_tall) || TextUtils.isEmpty(lc_weight) || TextUtils.isEmpty(lc_bmi) || TextUtils.isEmpty(lc_fat)
                        || TextUtils.isEmpty(lc_target) || TextUtils.isEmpty(lc_remark)) {
                    return;
                }
                showLoading();
                //自己修改自己，mb_no 傳空
                new NetWork(this).ChangeData(new ChangeDataReq(new ChangeDataReq.FormData("", Global.cinchData.getLc_id(),
                        lc_name, lc_tel, lc_age, lc_sex, lc_tall, lc_weight, lc_bmi, lc_fat, lc_target, lc_remark)), new ReqCallBack<BasicResponseBody<Object>>() {
                    @Override
                    public void onReqSuccess(BasicResponseBody<Object> result) {
                        showToast(result.getMsg());
                        finish();
                        dismissLoading();
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
