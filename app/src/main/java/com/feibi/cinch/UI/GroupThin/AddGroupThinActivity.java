package com.feibi.cinch.UI.GroupThin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.feibi.cinch.Adapter.RankAdapter;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;

import java.util.ArrayList;

public class AddGroupThinActivity extends BasicActivity {

    EditText et_task_name, et_task_info, et_task_time, et_task_people_num, et_target, et_scoring_method;
    RecyclerView rv_ranking;
    TextView tv_confirm_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_thin);
        findViewById(R.id.iv_back).setOnClickListener(this);
        et_task_name = findViewById(R.id.et_task_name);
        et_task_info = findViewById(R.id.et_task_info);
        et_task_time = findViewById(R.id.et_task_time);
        et_task_people_num = findViewById(R.id.et_task_people_num);
        et_target = findViewById(R.id.et_target);
        et_scoring_method = findViewById(R.id.et_scoring_method);
        rv_ranking = findViewById(R.id.rv_ranking);
        tv_confirm_add = findViewById(R.id.tv_confirm_add);
        findViewById(R.id.tv_add).setOnClickListener(this);

        ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        RankAdapter rankAdapter = new RankAdapter(this,data,true);
        rv_ranking.setLayoutManager(new LinearLayoutManager(this));
        rv_ranking.setAdapter(rankAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
