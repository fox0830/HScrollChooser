package com.feibi.cinch.UI.AddFriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.feibi.cinch.R;
import com.feibi.cinch.UI.Basic.BasicActivity;

public class FriendDataActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_data);
        findViewById(R.id.iv_back).setOnClickListener(this);
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
