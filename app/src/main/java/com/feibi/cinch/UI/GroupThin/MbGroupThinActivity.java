package com.feibi.cinch.UI.GroupThin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.feibi.cinch.Adapter.FriendAdapter;
import com.feibi.cinch.Adapter.GropActAdapter;
import com.feibi.cinch.Adapter.OverGroupActAdapter;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Account.RegisterActivity;
import com.feibi.cinch.UI.AddFriend.MyFriendActivity;
import com.feibi.cinch.UI.Basic.BasicActivity;

import java.util.ArrayList;

public class MbGroupThinActivity extends BasicActivity {

    TextView tv_no_info;
    RecyclerView rv_group_thin;
    RecyclerView rv_over_group_thin;
    GropActAdapter gropActAdapter;
    OverGroupActAdapter overGroupActAdapter;
    ArrayList<String> data = new ArrayList<>();
    EditText et_input;
    LinearLayout ll_no_result;
    TextView tv_ing, tv_over;
    boolean isIng = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mb_group_thin);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_clear_input).setOnClickListener(this);
        findViewById(R.id.iv_add_group).setOnClickListener(this);
        tv_ing = findViewById(R.id.tv_ing);
        tv_ing.setOnClickListener(this);
        tv_over = findViewById(R.id.tv_over);
        tv_over.setOnClickListener(this);
        tv_no_info = findViewById(R.id.tv_no_info);
        et_input = findViewById(R.id.et_input);
        ll_no_result = findViewById(R.id.ll_no_result);

        initAdapter();

        rv_group_thin = findViewById(R.id.rv_group_thin);
        rv_group_thin.setLayoutManager(new LinearLayoutManager(this));
        rv_group_thin.setAdapter(gropActAdapter);

        rv_over_group_thin = findViewById(R.id.rv_over_group_thin);
        rv_over_group_thin.setLayoutManager(new LinearLayoutManager(this));
        rv_over_group_thin.setAdapter(overGroupActAdapter);

        refreshUI();
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
            case R.id.tv_ing:
                isIng = true;
                tv_no_info.setText(getString(R.string.find_no_old_friend));
                refreshUI();
                break;
            case R.id.tv_over:
                isIng = false;
                tv_no_info.setText(getString(R.string.find_no_new_friend));
                refreshUI();
                break;
            case R.id.iv_clear_input:
                et_input.setText("");
                break;
            case R.id.iv_add_group:
                startActivity(new Intent(this, AddGroupThinActivity.class));
                break;
        }
    }

    private void refreshUI() {
        tv_ing.setBackground(isIng ? getDrawable(R.drawable.ic_check_btn) : null);
        tv_ing.setTextColor(isIng ? getResources().getColor(R.color.white) : getResources().getColor(R.color.yellow));
        tv_over.setBackground(isIng ? null : getDrawable(R.drawable.ic_check_btn));
        tv_over.setTextColor(isIng ? getResources().getColor(R.color.yellow) : getResources().getColor(R.color.white));
        rv_group_thin.setVisibility(isIng?View.VISIBLE:View.GONE);
        rv_over_group_thin.setVisibility(isIng?View.GONE:View.VISIBLE);
        ll_no_result.setVisibility(isIng?(gropActAdapter.getItemCount()<=0?View.VISIBLE:View.GONE):(overGroupActAdapter.getItemCount()<=0?View.VISIBLE:View.GONE));

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
            public void onClick(int pos, View view) {
                PopupMenu popup = new PopupMenu(MbGroupThinActivity.this, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.control_friend, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.see_task:
                                break;
                            case R.id.delete_task:
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
        overGroupActAdapter = new OverGroupActAdapter(this, data);
    }
}
