package com.feibi.cinch.UI.AddFriend;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
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
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Account.RegisterActivity;
import com.feibi.cinch.UI.Basic.BasicActivity;

import java.util.ArrayList;

public class MyFriendActivity extends BasicActivity {

    TextView tv_no_info;
    RecyclerView rv_friend;
    FriendAdapter friendAdapter;
    ArrayList<String> data = new ArrayList<>();
    EditText et_input;
    LinearLayout ll_no_result;
    TextView tv_old_friend, tv_new_friend;
    boolean isOldFriend = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_friend);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_clear_input).setOnClickListener(this);
        findViewById(R.id.iv_add_friend).setOnClickListener(this);
        tv_old_friend = findViewById(R.id.tv_old_friend);
        tv_old_friend.setOnClickListener(this);
        tv_new_friend = findViewById(R.id.tv_new_friend);
        tv_new_friend.setOnClickListener(this);
        tv_no_info = findViewById(R.id.tv_no_info);
        et_input = findViewById(R.id.et_input);
        ll_no_result = findViewById(R.id.ll_no_result);
        rv_friend = findViewById(R.id.rv_friend);
        rv_friend.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        rv_friend.setAdapter(friendAdapter);
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
            case R.id.tv_old_friend:
                isOldFriend = true;
                tv_no_info.setText(getString(R.string.find_no_old_friend));
                refreshUI();
                break;
            case R.id.tv_new_friend:
                isOldFriend = false;
                tv_no_info.setText(getString(R.string.find_no_new_friend));
                refreshUI();
                break;
            case R.id.iv_clear_input:
                et_input.setText("");
                break;
            case R.id.iv_add_friend:
                startActivity(new Intent(this, RegisterActivity.class).putExtra("register", false));
                break;
        }
    }

    private void refreshUI() {
        tv_old_friend.setBackground(isOldFriend ? getDrawable(R.drawable.ic_check_btn) : null);
        tv_old_friend.setTextColor(isOldFriend ? getResources().getColor(R.color.white) : getResources().getColor(R.color.yellow));
        tv_new_friend.setBackground(isOldFriend ? null : getDrawable(R.drawable.ic_check_btn));
        tv_new_friend.setTextColor(isOldFriend ? getResources().getColor(R.color.yellow) : getResources().getColor(R.color.white));

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
        friendAdapter = new FriendAdapter(this, data, new FriendAdapter.OnItemClickListener() {
            @Override
            public void onClick(int pos, View view) {
                PopupMenu popup = new PopupMenu(MyFriendActivity.this, view);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.control_friend, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.see_friend:
                                break;
                            case R.id.delete_friend:
                                break;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }
}
