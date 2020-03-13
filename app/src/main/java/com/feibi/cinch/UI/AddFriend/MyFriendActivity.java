package com.feibi.cinch.UI.AddFriend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.feibi.cinch.Adapter.FriendAdapter;
import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.module.Member;
import com.feibi.cinch.NetWork.request.GetFriendListReq;
import com.feibi.cinch.NetWork.respond.FriendData;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Account.RegisterActivity;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.UI.View.CustomDialog;
import com.feibi.cinch.utils.Global;
import com.feibi.cinch.utils.GsonUtil;
import com.feibi.cinch.utils.PreferencesUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

public class MyFriendActivity extends BasicActivity {

    TextView tv_no_info;
    RecyclerView rv_new_friend;
    RecyclerView rv_old_friend;
    FriendAdapter oldFriendAdapter;
    FriendAdapter newFriendAdapter;
    ArrayList<FriendData> oldFriendDataArrayList = new ArrayList<>();
    ArrayList<FriendData> newFriendDataArrayList = new ArrayList<>();
    EditText et_input;
    LinearLayout ll_no_result;
    TextView tv_old_friend, tv_new_friend;
    boolean isOldFriend = true;
    CustomDialog deleteDialog;

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
        rv_old_friend = findViewById(R.id.rv_old_friend);
        rv_new_friend = findViewById(R.id.rv_new_friend);
        rv_old_friend.setLayoutManager(new LinearLayoutManager(this));
        rv_new_friend.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        rv_old_friend.setAdapter(oldFriendAdapter);
        rv_new_friend.setAdapter(newFriendAdapter);
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
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_delete, null);
        deleteDialog = new CustomDialog(this, dialogView);
        getFriends();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_old_friend:
                isOldFriend = true;
                refreshUI();
                break;
            case R.id.tv_new_friend:
                isOldFriend = false;
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

    private void getFriends() {
        Global.MbNo = PreferencesUtil.getMbNo(this);
        new Member(this).GetArrayList(new BasicReq("listlc",new GetFriendListReq("o", Global.MbNo)), new ReqCallBack<BasicResponseBody<ArrayList>>() {
            @Override
            public void onReqSuccess(BasicResponseBody<ArrayList> result) {
                oldFriendDataArrayList.clear();
                for (int i = 0; i < result.getData().size(); i++) {
                    String json = new Gson().toJson(result.getData().get(i));
                    try {
                        FriendData friendData = (FriendData) GsonUtil.str2Obj(json, FriendData.class);
                        if ("o".equals(friendData.getOn_status()))
                            oldFriendDataArrayList.add(friendData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                oldFriendAdapter.notifyDataSetChanged();
                refreshUI();
            }

            @Override
            public void onReqFailed(BasicResponseBody result) {
                showToast(result.getMsg());
            }
        });
        new Member(this).GetArrayList(new BasicReq("listlc",new GetFriendListReq("n", Global.MbNo)), new ReqCallBack<BasicResponseBody<ArrayList>>() {
            @Override
            public void onReqSuccess(BasicResponseBody<ArrayList> result) {
                newFriendDataArrayList.clear();
                for (int i = 0; i < result.getData().size(); i++) {
                    String json = new Gson().toJson(result.getData().get(i));
                    try {
                        FriendData friendData = (FriendData) GsonUtil.str2Obj(json, FriendData.class);
                        if ("n".equals(friendData.getOn_status()))
                            newFriendDataArrayList.add(friendData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                newFriendAdapter.notifyDataSetChanged();
                refreshUI();
            }

            @Override
            public void onReqFailed(BasicResponseBody result) {
                showToast(result.getMsg());
            }
        });
    }

    private void refreshUI() {
        tv_no_info.setText(isOldFriend ? getString(R.string.find_no_old_friend) : getString(R.string.find_no_new_friend));
        tv_old_friend.setBackground(isOldFriend ? getDrawable(R.drawable.ic_check_btn) : null);
        tv_old_friend.setTextColor(isOldFriend ? getResources().getColor(R.color.white) : getResources().getColor(R.color.yellow));
        tv_new_friend.setBackground(isOldFriend ? null : getDrawable(R.drawable.ic_check_btn));
        tv_new_friend.setTextColor(isOldFriend ? getResources().getColor(R.color.yellow) : getResources().getColor(R.color.white));
        rv_new_friend.setVisibility(isOldFriend ? View.INVISIBLE : View.VISIBLE);
        rv_old_friend.setVisibility(isOldFriend ? View.VISIBLE : View.INVISIBLE);
        if (isOldFriend) {
            ll_no_result.setVisibility(oldFriendAdapter.getItemCount() > 0 ? View.INVISIBLE : View.VISIBLE);
        } else {
            ll_no_result.setVisibility(newFriendAdapter.getItemCount() > 0 ? View.INVISIBLE : View.VISIBLE);
        }
    }

    FriendAdapter.OnItemClickListener onItemClickListener = new FriendAdapter.OnItemClickListener() {
        @Override
        public void onClick(int pos, View view) {
            PopupMenu popup = new PopupMenu(MyFriendActivity.this, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.control_friend, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.see_friend:
                            String CinchId = "";
                            if(isOldFriend){
                                CinchId = oldFriendAdapter.getItem(pos).getLc_id();
                            }else {
                                CinchId = newFriendAdapter.getItem(pos).getLc_id();
                            }
                            if(TextUtils.isEmpty(CinchId)){
                                showToast(getString(R.string.get_useid_err));
                            }else {
                                startActivity(new Intent(MyFriendActivity.this, FriendDataActivity.class).putExtra("CinchId",CinchId));
                            }
                            break;
                        case R.id.delete_friend:
                            deleteDialog.show();
                            break;
                    }
                    return true;
                }
            });
            popup.show();
        }
    };

    private void initAdapter() {
        oldFriendAdapter = new FriendAdapter(this, oldFriendDataArrayList, onItemClickListener);
        newFriendAdapter = new FriendAdapter(this, newFriendDataArrayList, onItemClickListener);
    }
}
