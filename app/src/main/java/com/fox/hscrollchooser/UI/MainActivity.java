package com.fox.hscrollchooser.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fox.hscrollchooser.Adapter.ScaleAdapter;
import com.fox.hscrollchooser.R;
import com.fox.hscrollchooser.UI.View.BottomDialog;
import com.fox.hscrollchooser.UI.View.CenterLayoutManager;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    com.fox.hscrollchooser.UI.View.BottomDialog chooseDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initChooser();
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chooseDialog!=null)
                chooseDialog.show();
            }
        });
    }

    /**
     * 初始化橫向滑動選擇器
     */
    public void initChooser() {
        //设置身高数据
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


        //设置体重数据
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


        //设置体脂数据
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
        //設置初始位置
        rv_height.smoothScrollToPosition(10);
        rv_weight.smoothScrollToPosition(10);
        rv_fat.smoothScrollToPosition(10);
        tv_height_value.setText(heightDatas.get(10));
        tv_weight_value.setText(weightDatas.get(10));
        tv_fat_value.setText(fatDatas.get(10));
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
            }
        });
        chooseDialog = new BottomDialog(this, dialogView);
    }
}
