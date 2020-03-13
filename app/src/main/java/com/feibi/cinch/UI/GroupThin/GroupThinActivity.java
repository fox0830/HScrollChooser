package com.feibi.cinch.UI.GroupThin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feibi.cinch.Adapter.GropActAdapter;
import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.module.Team;
import com.feibi.cinch.NetWork.request.OnlyLcIdReq;
import com.feibi.cinch.NetWork.respond.TaskData;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.utils.Global;
import com.feibi.cinch.utils.GsonUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class GroupThinActivity extends BasicActivity {

    RecyclerView rv_activity;
    GropActAdapter gropActAdapter;
    ArrayList<TaskData> data = new ArrayList<>();
    EditText et_input;
    LinearLayout ll_no_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_thin);
        rv_activity = findViewById(R.id.rv_activity);
        et_input = findViewById(R.id.et_input);
        ll_no_result = findViewById(R.id.ll_no_result);
        findViewById(R.id.iv_clear_input).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_scan).setOnClickListener(this);
        rv_activity.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        rv_activity.setAdapter(gropActAdapter);
        et_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    showToast(et_input.getText().toString());
                    return true;
                }
                return false;
            }
        });
        getGroupThin();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_scan:
                startActivity(new Intent(this, ScanQRActivity.class));
                break;
            case R.id.iv_clear_input:
                et_input.setText("");
                break;
        }
    }
    public void getGroupThin(){
        showLoading();
        new Team(this).GetArrayList(new BasicReq("lctasklist", new OnlyLcIdReq(Global.cinchData.getLc_id())), new ReqCallBack<BasicResponseBody<ArrayList>>() {
            @Override
            public void onReqSuccess(BasicResponseBody<ArrayList> result) {
                data.clear();
                for (int i = 0; i < result.getData().size(); i++) {
                    String json = new Gson().toJson(result.getData().get(i));
                    try {
                        TaskData taskData = (TaskData) GsonUtil.str2Obj(json, TaskData.class);
                        data.add(taskData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                gropActAdapter.notifyDataSetChanged();
                dismissLoading();
                ll_no_result.setVisibility(gropActAdapter.getItemCount()>0?View.VISIBLE:View.GONE);
            }

            @Override
            public void onReqFailed(BasicResponseBody result) {
                showToast(result.getMsg());
                dismissLoading();
            }
        });
    }

    private void initAdapter() {
        gropActAdapter = new GropActAdapter(this, data, new GropActAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos, View view) {

            }
        });
    }
}
