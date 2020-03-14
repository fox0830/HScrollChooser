package com.feibi.cinch.UI.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.feibi.cinch.Adapter.ScaleAdapter;
import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.module.Member;
import com.feibi.cinch.NetWork.request.ChangeDataReq;
import com.feibi.cinch.NetWork.request.GetBmiReq;
import com.feibi.cinch.NetWork.respond.CinchData;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.UI.View.BottomDialog;
import com.feibi.cinch.UI.View.CenterLayoutManager;
import com.feibi.cinch.utils.Global;
import com.feibi.cinch.utils.PreferencesUtil;

import java.util.ArrayList;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class ChangeDataActivity extends BasicActivity {

    ImageView iv_head;
    EditText et_name, et_age, et_tel, et_target, et_remark, et_suggest;
    RadioButton rb_male, rb_female;
    TextView tv_height, tv_weight, tv_fat, tv_bmi;
    LinearLayout ll_suggest;
    BottomDialog chooseDialog;

    CinchData seeCinchData = new CinchData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        check = true;
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
        et_suggest = findViewById(R.id.et_suggest);
        ll_suggest = findViewById(R.id.ll_suggest);

        if(isMb()){

        }else {
            seeCinchData = Global.cinchData;
        }
        et_name.setText(seeCinchData.getLc_name()!=null?seeCinchData.getLc_name():"");
        et_age.setText(seeCinchData.getLc_age()!=null?seeCinchData.getLc_age():"");
        et_tel.setText(seeCinchData.getLc_tel()!=null?seeCinchData.getLc_tel():"");
        tv_height.setText(seeCinchData.getLc_tall()!=null?seeCinchData.getLc_tall():"");
        tv_weight.setText(seeCinchData.getLc_weight()!=null?seeCinchData.getLc_weight():"");
        tv_fat.setText(seeCinchData.getLc_fat()!=null?seeCinchData.getLc_fat():"");
        tv_bmi.setText(seeCinchData.getLc_bmi()!=null?seeCinchData.getLc_bmi():"");
        et_target.setText(seeCinchData.getLc_target()!=null?seeCinchData.getLc_target():"");
        et_remark.setText(seeCinchData.getLc_remark()!=null?seeCinchData.getLc_remark():"");
        rb_male.setChecked("1".equals(seeCinchData.getLc_sex()));
        rb_female.setChecked("0".equals(seeCinchData.getLc_sex()));
        /**
         * 判斷用戶類型
         */
        ll_suggest.setVisibility(isMb() ? View.VISIBLE : View.GONE);
        /**
         * 初始化橫向滑動選擇器
         */
        initChooser();

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
                if(chooseDialog!=null)
                    chooseDialog.show();
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
                String mb_no = isMb() ? Global.MbNo : "";
                new Member(this).GetObject(new BasicReq("modifylc", new ChangeDataReq(mb_no, seeCinchData.getLc_id(),
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

                //todo 修改講義產品
                break;
        }
    }

    /**
     * 初始化橫向滑動選擇器
     */
    public void initChooser() {
        ArrayList<String> heightDatas = new ArrayList<>();
        for (int i = 100; i <= 220; i++) {
            heightDatas.add(i + "");
        }
        RecyclerView rv_height;
        TextView tv_height_value;
        LinearSnapHelper mLinearSnapHelper;
        ScaleAdapter mAdapter = new ScaleAdapter(this, heightDatas);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_scroll_choose, null);
        rv_height = dialogView.findViewById(R.id.rv_height);
        tv_height_value = dialogView.findViewById(R.id.tv_height_value);
        mLinearSnapHelper = new LinearSnapHelper();
        mLinearSnapHelper.attachToRecyclerView(rv_height);
        CenterLayoutManager layoutManager = new CenterLayoutManager(this);
        layoutManager.setOrientation(CenterLayoutManager.HORIZONTAL);
        rv_height.setLayoutManager(layoutManager);
        rv_height.setAdapter(mAdapter);
        rv_height.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean mScrolled = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mScrolled) {
                    mScrolled = false;
                    View tV = mLinearSnapHelper.findSnapView(rv_height.getLayoutManager());
                    if (tV == null) {
                        return;
                    }
                    RecyclerView.LayoutManager manager = rv_height.getLayoutManager();
                    if (manager instanceof LinearLayoutManager) {
                        LinearLayoutManager linearManager = (LinearLayoutManager) manager;
                        //获取第一个可见view的位置
                        int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                        int lastItemPosition = linearManager.findLastVisibleItemPosition();
                        for (int i = firstItemPosition; i <= lastItemPosition; i++) {
                            if (tV.equals(linearManager.findViewByPosition(i))) {
                                tv_height_value.setText(heightDatas.get(i - 1));
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx != 0 || dy != 0) {
                    mScrolled = true;
                }
            }
        });

        ArrayList<String> weightDatas = new ArrayList<>();
        for (int i = 40; i <= 150; i++) {
            weightDatas.add(i + "");
        }
        RecyclerView rv_weight;
        TextView tv_weight_value;
        LinearSnapHelper mLinearSnapHelper_W;
        ScaleAdapter mAdapter_W = new ScaleAdapter(this, weightDatas);
        rv_weight = dialogView.findViewById(R.id.rv_weight);
        tv_weight_value = dialogView.findViewById(R.id.tv_weight_value);
        mLinearSnapHelper_W = new LinearSnapHelper();
        mLinearSnapHelper_W.attachToRecyclerView(rv_weight);
        CenterLayoutManager layoutManager_w = new CenterLayoutManager(this);
        layoutManager_w.setOrientation(CenterLayoutManager.HORIZONTAL);
        rv_weight.setLayoutManager(layoutManager_w);
        rv_weight.setAdapter(mAdapter_W);
        rv_weight.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean mScrolled = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mScrolled) {
                    mScrolled = false;
                    View tV = mLinearSnapHelper_W.findSnapView(rv_weight.getLayoutManager());
                    if (tV == null) {
                        return;
                    }
                    RecyclerView.LayoutManager manager = rv_weight.getLayoutManager();
                    if (manager instanceof LinearLayoutManager) {
                        LinearLayoutManager linearManager = (LinearLayoutManager) manager;
                        //获取第一个可见view的位置
                        int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                        int lastItemPosition = linearManager.findLastVisibleItemPosition();
                        for (int i = firstItemPosition; i <= lastItemPosition; i++) {
                            if (tV.equals(linearManager.findViewByPosition(i))) {
                                tv_weight_value.setText(weightDatas.get(i - 1));
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx != 0 || dy != 0) {
                    mScrolled = true;
                }
            }
        });

        ArrayList<String> fatDatas = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            fatDatas.add(i + "");
        }
        RecyclerView rv_fat;
        TextView tv_fat_value;
        LinearSnapHelper mLinearSnapHelper_fat;
        ScaleAdapter mAdapter_fat = new ScaleAdapter(this, fatDatas);

        rv_fat = dialogView.findViewById(R.id.rv_fat);
        tv_fat_value = dialogView.findViewById(R.id.tv_fat_value);
        mLinearSnapHelper_fat = new LinearSnapHelper();
        mLinearSnapHelper_fat.attachToRecyclerView(rv_fat);
        CenterLayoutManager layoutManager_f = new CenterLayoutManager(this);
        layoutManager_f.setOrientation(CenterLayoutManager.HORIZONTAL);
        rv_fat.setLayoutManager(layoutManager_f);
        rv_fat.setAdapter(mAdapter_fat);
        rv_fat.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean mScrolled = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && mScrolled) {
                    mScrolled = false;
                    View tV = mLinearSnapHelper_fat.findSnapView(rv_fat.getLayoutManager());
                    if (tV == null) {
                        return;
                    }
                    RecyclerView.LayoutManager manager = rv_fat.getLayoutManager();
                    if (manager instanceof LinearLayoutManager) {
                        LinearLayoutManager linearManager = (LinearLayoutManager) manager;
                        //获取第一个可见view的位置
                        int firstItemPosition = linearManager.findFirstVisibleItemPosition();
                        int lastItemPosition = linearManager.findLastVisibleItemPosition();
                        for (int i = firstItemPosition; i <= lastItemPosition; i++) {
                            if (tV.equals(linearManager.findViewByPosition(i))) {
                                tv_fat_value.setText(fatDatas.get(i - 1));
                                return;
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dx != 0 || dy != 0) {
                    mScrolled = true;
                }
            }
        });
        rv_height.scrollToPosition(1);
        rv_weight.scrollToPosition(1);
        rv_fat.scrollToPosition(1);
        String tall = tv_height.getText().toString();
        String weight = tv_weight.getText().toString();
        String fat = tv_fat.getText().toString();
        tv_height_value.setText(tall);
        tv_weight_value.setText(weight);
        tv_fat_value.setText(fat);
        for(int i =0;i<heightDatas.size();i++){
            if(heightDatas.get(i).equals(tv_height.getText().toString())){
                rv_height.scrollToPosition(i+1);
                rv_height.smoothScrollToPosition(i+1);
            }
        }
        for(int i =0;i<weightDatas.size();i++){
            if(weightDatas.get(i).equals(tv_weight.getText().toString())){
                rv_weight.scrollToPosition(i+1);
                rv_weight.smoothScrollToPosition(i+1);
            }
        }
        for(int i =0;i<fatDatas.size();i++){
            if(fatDatas.get(i).equals(tv_fat.getText().toString())){
                rv_fat.scrollToPosition(i+1);
                rv_fat.smoothScrollToPosition(i+1);
            }
        }

        dialogView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDialog.dismiss();
            }
        });
        dialogView.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseDialog.dismiss();
                String tall = tv_height_value.getText().toString();
                String weight = tv_weight_value.getText().toString();
                tv_height.setText(tall);
                tv_weight.setText(weight);
                tv_fat.setText(tv_fat_value.getText().toString());
                showLoading();
                new Member(ChangeDataActivity.this).GetObject(new BasicReq("bmi",new GetBmiReq(tall, weight)), new ReqCallBack<BasicResponseBody<Object>>() {
                    @Override
                    public void onReqSuccess(BasicResponseBody<Object> result) {
                        dismissLoading();
                        tv_bmi.setText(result.getData().toString());
                    }

                    @Override
                    public void onReqFailed(BasicResponseBody result) {
                        dismissLoading();
                        showToast("BMI計算出錯：" + result.getMsg());
                    }
                });
            }
        });
        chooseDialog = new BottomDialog(this, dialogView);
    }

}
