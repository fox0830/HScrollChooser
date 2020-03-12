package com.feibi.cinch.UI.GroupThin;

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
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;

import java.util.ArrayList;

public class GroupThinActivity extends BasicActivity {

    RecyclerView rv_activity;
    GropActAdapter gropActAdapter;
    ArrayList<String> data = new ArrayList<>();
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_scan:

                break;
            case R.id.iv_clear_input:
                et_input.setText("");
                break;
        }
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
        gropActAdapter = new GropActAdapter(this, data, new GropActAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos) {
                showToast(pos + "");
            }
        });
    }
}
