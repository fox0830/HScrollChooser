package com.feibi.cinch.UI.Account;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.feibi.cinch.Adapter.GropActAdapter;
import com.feibi.cinch.Adapter.JoinTaskAdapter;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.utils.Global;

import java.util.ArrayList;

public class JoinTaskActivity extends BasicActivity {
    RecyclerView rv_task;
    JoinTaskAdapter joinTaskAdapter;
    ArrayList<String> data = new ArrayList<>();

    ImageView iv_user_head;
    TextView tv_name,tv_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_task);
        rv_task = findViewById(R.id.rv_task);
        rv_task.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        rv_task.setAdapter(joinTaskAdapter);

        iv_user_head=findViewById(R.id.iv_user_head);
        tv_name=findViewById(R.id.tv_name);
        tv_id=findViewById(R.id.tv_id);
        tv_name.setText(Global.cinchData.getLc_name());
        tv_id.setText("ID:"+Global.cinchData.getLc_id());
    }

    @Override
    public void onClick(View view) {

    }
    private void initAdapter() {
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        joinTaskAdapter = new JoinTaskAdapter(this, data, new JoinTaskAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                showToast(pos + "");
            }
        });
    }
}
