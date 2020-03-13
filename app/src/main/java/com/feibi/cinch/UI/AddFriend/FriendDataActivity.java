package com.feibi.cinch.UI.AddFriend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.module.Member;
import com.feibi.cinch.NetWork.request.GetCinchUserDataReq;
import com.feibi.cinch.NetWork.respond.CinchData;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Account.JoinTaskActivity;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.utils.Global;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class FriendDataActivity extends BasicActivity {

    String lc_id;
    ImageView iv_user_head;
    TextView tv_name, tv_age, tv_tel, tv_height, tv_weight, tv_fat, tv_bmi, tv_target, tv_remark;
    CinchData cinchData = new CinchData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_data);
        findViewById(R.id.iv_back).setOnClickListener(this);
        lc_id = getIntent().getStringExtra("CinchId");

        iv_user_head = findViewById(R.id.iv_user_head);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        tv_tel = findViewById(R.id.tv_tel);
        tv_height = findViewById(R.id.tv_height);
        tv_weight = findViewById(R.id.tv_weight);
        tv_fat = findViewById(R.id.tv_fat);
        tv_bmi = findViewById(R.id.tv_bmi);
        tv_target = findViewById(R.id.tv_target);
        tv_remark = findViewById(R.id.tv_remark);
        getUserData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.ll_task:
                startActivity(new Intent(this, JoinTaskActivity.class));
                break;
        }
    }

    public void getUserData() {
        showLoading();
        new Member(this).GetCinchData(new BasicReq("getlc",new GetCinchUserDataReq(Global.MbNo, lc_id)), new ReqCallBack<BasicResponseBody<CinchData>>() {
            @Override
            public void onReqSuccess(BasicResponseBody<CinchData> result) {
                dismissLoading();
                cinchData = result.getData();
                refreshDataUI();
            }

            @Override
            public void onReqFailed(BasicResponseBody result) {
                dismissLoading();
                showToast(result.getMsg());
            }
        });
//        new Member(this).GetCinchUserData(new GetCinchUserDataReq(new GetCinchUserDataReq.FormData("", lc_id)), new ReqCallBack<BasicResponseBody<CinchData>>() {
//            @Override
//            public void onReqSuccess(BasicResponseBody<CinchData> result) {
//                cinchData = result.getData();
//                dismissLoading();
//                refreshDataUI();
//            }
//
//            @Override
//            public void onReqFailed(BasicResponseBody result) {
//                showToast(getString(R.string.get_data_err));
//                dismissLoading();
//            }
//        });
    }

    protected void refreshDataUI() {
        if (cinchData == null) {
            return;
        }
        Glide.with(this).load(cinchData.getLc_pic()).into(iv_user_head);
        tv_name.setText(cinchData.getLc_name());
        tv_age.setText(cinchData.getLc_age() + getString(R.string.age_unit));
        tv_tel.setText(cinchData.getLc_tel());
        tv_height.setText(cinchData.getLc_tall());
        tv_weight.setText(cinchData.getLc_weight());
        tv_fat.setText(cinchData.getLc_fat());
        tv_bmi.setText(cinchData.getLc_bmi());
        tv_target.setText(cinchData.getLc_target());
        tv_remark.setText(cinchData.getLc_remark());
    }
}
