package com.feibi.cinch.UI.Account;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feibi.cinch.NetWork.module.NetWork;
import com.feibi.cinch.NetWork.request.GetCinchUserDataReq;
import com.feibi.cinch.NetWork.request.UploadPicReq;
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

public class PersonalDataActivity extends BasicActivity {

    ImageView iv_user_head;

    TextView tv_name, tv_age, tv_tel, tv_height, tv_weight, tv_fat, tv_bmi, tv_target, tv_remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.ll_change_pwd).setOnClickListener(this);
        findViewById(R.id.ll_logout).setOnClickListener(this);
        findViewById(R.id.ll_change_data).setOnClickListener(this);
        findViewById(R.id.ll_task).setOnClickListener(this);
        iv_user_head = findViewById(R.id.iv_user_head);
        iv_user_head.setOnClickListener(this);

        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        tv_tel = findViewById(R.id.tv_tel);
        tv_height = findViewById(R.id.tv_height);
        tv_weight = findViewById(R.id.tv_weight);
        tv_fat = findViewById(R.id.tv_fat);
        tv_bmi = findViewById(R.id.tv_bmi);
        tv_target = findViewById(R.id.tv_target);
        tv_remark = findViewById(R.id.tv_remark);

        tv_name.setText(Global.cinchData.getLc_name());
        tv_age.setText(Global.cinchData.getLc_age()+getString(R.string.age_unit));
        tv_tel.setText(Global.cinchData.getLc_tel());
        tv_height.setText(Global.cinchData.getLc_tall());
        tv_weight.setText(Global.cinchData.getLc_weight());
        tv_fat.setText(Global.cinchData.getLc_fat());
        tv_bmi.setText(Global.cinchData.getLc_bmi());
        tv_target.setText(Global.cinchData.getLc_target());
        tv_remark.setText(Global.cinchData.getLc_remark());
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLoading();
        new NetWork(this).GetCinchUserData(new GetCinchUserDataReq(new GetCinchUserDataReq.FormData("", Global.cinchData.getLc_id())), new ReqCallBack<BasicResponseBody<CinchData>>() {
            @Override
            public void onReqSuccess(BasicResponseBody<CinchData> result) {
                    Global.cinchData = result.getData();
                    dismissLoading();
            }

            @Override
            public void onReqFailed(BasicResponseBody result) {
                showToast(getString(R.string.get_data_err));
                dismissLoading();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_task:
                startActivity(new Intent(this,JoinTaskActivity.class));
                break;
            case R.id.ll_logout:
                PreferencesUtil.saveUseType(this, "");
                PreferencesUtil.saveCinchData(this, "");
                Global.cinchData = new CinchData();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.ll_change_pwd:
                startActivity(new Intent(this, ChangePwdActivity.class));
                break;
            case R.id.ll_change_data:
                startActivity(new Intent(this, ChangeDataActivity.class));
                break;
        }
    }

}
