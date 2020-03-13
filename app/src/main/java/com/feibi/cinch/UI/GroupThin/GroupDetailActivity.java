package com.feibi.cinch.UI.GroupThin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.bumptech.glide.Glide;
import com.feibi.cinch.Adapter.GropActAdapter;
import com.feibi.cinch.Adapter.OverGroupActAdapter;
import com.feibi.cinch.Adapter.TaskPeopleAdapter;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;

import java.util.ArrayList;

public class GroupDetailActivity extends BasicActivity {
    ImageView iv_qr_code;
    RecyclerView rv_task_people;
    TaskPeopleAdapter taskPeopleAdapter;
    ArrayList<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        findViewById(R.id.iv_back).setOnClickListener(this);
        iv_qr_code = findViewById(R.id.iv_qr_code);
        rv_task_people = findViewById(R.id.rv_task_people);
        initAdapter();
        Glide.with(this).load("http://img0.imgtn.bdimg.com/it/u=376029017,2622921745&fm=26&gp=0.jpg").into(iv_qr_code);
        rv_task_people.setLayoutManager(new LinearLayoutManager(this));
        rv_task_people.setAdapter(taskPeopleAdapter);
        rv_task_people.setNestedScrollingEnabled(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void initAdapter() {
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        taskPeopleAdapter = new TaskPeopleAdapter(this, data, new TaskPeopleAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos, View view) {
                startActivity(new Intent(GroupDetailActivity.this, PersonalTaskDetailActivity.class));
            }
        });
    }
}
