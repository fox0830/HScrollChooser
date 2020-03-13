package com.feibi.cinch.UI.AddFriend;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.feibi.cinch.NetWork.basic.BasicReq;
import com.feibi.cinch.NetWork.module.Member;
import com.feibi.cinch.NetWork.request.GetCinchUserDataReq;
import com.feibi.cinch.NetWork.respond.CinchData;
import com.feibi.cinch.R;
import com.feibi.cinch.UI.Account.JoinTaskActivity;
import com.feibi.cinch.UI.Basic.BasicActivity;
import com.feibi.cinch.UI.View.CircleProgressBar;
import com.feibi.cinch.utils.Global;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jh.app.android.basiclibrary.entity.BasicResponseBody;
import jh.app.android.basiclibrary.network.ReqCallBack;

import static com.github.mikephil.charting.components.Legend.LegendForm.LINE;

public class FriendDataActivity extends BasicActivity {

    String lc_id;
    ImageView iv_user_head, iv_sex;
    TextView tv_name, tv_age, tv_tel, tv_height, tv_weight, tv_fat, tv_bmi, tv_target, tv_remark;
    CinchData cinchData = new CinchData();

    TextView tv_answer, tv_suggest_prop;
    CircleProgressBar circle_progress_bar;

    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_data);
        findViewById(R.id.iv_back).setOnClickListener(this);
        lc_id = getIntent().getStringExtra("CinchId");

        iv_user_head = findViewById(R.id.iv_user_head);
        tv_name = findViewById(R.id.tv_name);
        tv_age = findViewById(R.id.tv_age);
        iv_sex = findViewById(R.id.iv_sex);
        tv_tel = findViewById(R.id.tv_tel);
        tv_height = findViewById(R.id.tv_height);
        tv_weight = findViewById(R.id.tv_weight);
        tv_fat = findViewById(R.id.tv_fat);
        tv_bmi = findViewById(R.id.tv_bmi);
        tv_target = findViewById(R.id.tv_target);
        tv_remark = findViewById(R.id.tv_remark);
        /**
         * 直銷商可見
         */
        tv_answer = findViewById(R.id.tv_answer);
        tv_suggest_prop = findViewById(R.id.tv_suggest_prop);
        circle_progress_bar = findViewById(R.id.circle_progress_bar);
        circle_progress_bar.setCricleProgressColor(getResources().getColor(R.color.red));
        circle_progress_bar.setProgress(70);
        circle_progress_bar.setRoundWidth(6);

        lineChart = (LineChart) findViewById(R.id.lineChart);
        getUserData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.ll_task:
                startActivity(new Intent(this, JoinTaskActivity.class));
                break;
        }
    }

    /**
     * 初始化曲线图表
     */
    private void initLineChart(final ArrayList<Float> fats, final ArrayList<Float> weights) {
        //显示边界
        lineChart.setDrawBorders(false);
        //设置数据
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < fats.size(); i++) {
            entries.add(new Entry(i, fats.get(i)));
        }
        //一个LineDataSet就是一条线
        LineDataSet fatSet = new LineDataSet(entries, getString(R.string.fat) + "(%)");
        //线颜色
        fatSet.setColor(getResources().getColor(R.color.yellow));
        //线宽度
        fatSet.setLineWidth(3f);
        fatSet.setValueTextSize(12f);
        fatSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value + "%";
            }
        });
        fatSet.setValueTextColor(getResources().getColor(R.color.yellow));

        //显示圆点
        fatSet.setDrawCircleHole(false);
        fatSet.setDrawCircles(true);
        //线条平滑
        fatSet.setMode(LineDataSet.Mode.LINEAR);
        //设置折线图填充
//        fatSet.setDrawFilled(true);
        //无数据时显示的文字
        lineChart.setNoDataText("暫無數據");


        List<Entry> entries2 = new ArrayList<>();
        for (int i = 0; i < weights.size(); i++) {
            entries2.add(new Entry(i, weights.get(i) * 2));
        }
        //一个LineDataSet就是一条线
        LineDataSet weightSet = new LineDataSet(entries2, getString(R.string.weight) + "(kg)");
        //线颜色
        weightSet.setColor(getResources().getColor(R.color.high_green));
        //线宽度
        weightSet.setLineWidth(3f);
        weightSet.setValueTextSize(12f);
        weightSet.setValueTextColor(getResources().getColor(R.color.high_green));
        weightSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value + "kg";
            }
        });
        //显示圆点
        weightSet.setDrawCircleHole(false);
        weightSet.setDrawCircles(true);
        //线条平滑
        weightSet.setMode(LineDataSet.Mode.LINEAR);
        LineData data = new LineData(fatSet, weightSet);
        //折线图显示数值
        data.setDrawValues(true);


        //得到X轴
        XAxis xAxis = lineChart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(0f);
        //设置X轴的刻度数量，第二个参数为true,将会画出明确数量（带有小数点），但是可能值导致不均匀，默认（6，false）
        xAxis.setLabelCount(5, true);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(4);
        //不显示网格线
        xAxis.setDrawGridLines(false);
        // 标签倾斜
        xAxis.setLabelRotationAngle(0);
        xAxis.setTextColor(getResources().getColor(R.color.color_d2));
        //设置X轴值为字符串
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                int IValue = (int) value;
                String x = "";
                if (IValue <= 0) {
                    x = getString(R.string.start);
                }
                if (IValue == 1) {
                    x = getString(R.string.month1);
                }
                if (IValue == 2) {
                    x = getString(R.string.month2);
                }
                if (IValue == 3) {
                    x = getString(R.string.month3);
                }
                return x;
            }
        });


        //得到Y轴
        YAxis yAxis = lineChart.getAxisLeft();
        YAxis rightYAxis = lineChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //设置y轴坐标之间的最小间隔
        yAxis.setGranularity(20);
        //不显示网格线
        yAxis.setDrawGridLines(false);
        //设置y轴的刻度数量
        //+2：最大值n就有n+1个刻度，在加上y轴多一个单位长度，为了好看，so+2
        yAxis.setLabelCount(4 + 2, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);
        //+1:y轴多一个单位长度，为了好看
        yAxis.setAxisMaximum(140 + 1);
        yAxis.setTextColor(getResources().getColor(R.color.color_d2));
        //图例：得到Lengend
        Legend legend = lineChart.getLegend();
        //Lengend
        legend.setEnabled(true);
        legend.setForm(LINE);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        lineChart.setDescription(description);
        //折线图点的标记
//        MyMarkerView mv = new MyMarkerView(this);
//        lineChart.setMarker(mv);
        //设置数据
        lineChart.setTouchEnabled(false);
        lineChart.setData(data);
        lineChart.setExtraBottomOffset(10f);//防止底部数据显示不完整，设置底部偏移量

        //图标刷新
        lineChart.invalidate();
    }

    public void getUserData() {
        showLoading();
        new Member(this).GetCinchData(new BasicReq("getlc", new GetCinchUserDataReq(Global.MbNo, lc_id)), new ReqCallBack<BasicResponseBody<CinchData>>() {
            @Override
            public void onReqSuccess(BasicResponseBody<CinchData> result) {
                dismissLoading();
                cinchData = result.getData();
                refreshDataUI();
            }

            @Override
            public void onReqFailed(BasicResponseBody result) {
                dismissLoading();
                showToast(result.getMsg());
            }
        });
    }

    protected void refreshDataUI() {
        if (cinchData == null) {
            return;
        }
        if (cinchData.getLc_pic() != null) {
            Glide.with(this).load(cinchData.getLc_pic()).into(iv_user_head);
        }
        tv_name.setText(cinchData.getLc_name());
        tv_age.setText(cinchData.getLc_age() + getString(R.string.age_unit));
        tv_age.setBackground("1".equals(cinchData.getLc_sex()) ? getDrawable(R.drawable.blue_btn) : getDrawable(R.drawable.pink_btn));
        iv_sex.setImageDrawable("1".equals(cinchData.getLc_sex()) ? getDrawable(R.drawable.ic_male) : getDrawable(R.drawable.ic_female));
        tv_tel.setText(cinchData.getLc_tel());
        tv_height.setText(cinchData.getLc_tall());
        tv_weight.setText(cinchData.getLc_weight());
        tv_fat.setText(cinchData.getLc_fat());
        tv_bmi.setText(cinchData.getLc_bmi());
        tv_target.setText(cinchData.getLc_target());
        tv_remark.setText(cinchData.getLc_remark());

        String answer = "1、" + getAnswer(cinchData.getAns1()) + "    2、" + getAnswer(cinchData.getAns2()) + "    3、" + getAnswer(cinchData.getAns3())
                + "    4、" + getAnswer(cinchData.getAns4()) + "    5、" + getAnswer(cinchData.getAns5()) + "    6、" + getAnswer(cinchData.getAns6());
        tv_answer.setText(answer);
        String suggestProp = "";
        for (String prop : cinchData.getSuggest_prod()) {
            suggestProp = suggestProp + (prop + "    ");
        }
        tv_suggest_prop.setText(suggestProp);

        ArrayList<Float> fats = new ArrayList<>();
        String fat0 = cinchData.getMon1_fat().replace("%", ""); //開始
        String fat1 = cinchData.getMon1_fat().replace("%", "");
        String fat2 = cinchData.getMon2_fat().replace("%", "");
        String fat3 = cinchData.getMon3_fat().replace("%", "");

        fats.add(TextUtils.isEmpty(fat0) ? 0f : Float.parseFloat(fat0));
        fats.add(TextUtils.isEmpty(fat1) ? 0f : Float.parseFloat(fat1));
        fats.add(TextUtils.isEmpty(fat2) ? 0f : Float.parseFloat(fat2));
        fats.add(TextUtils.isEmpty(fat3) ? 0f : Float.parseFloat(fat3));

        ArrayList<Float> weights = new ArrayList<>();
        weights.add(TextUtils.isEmpty(cinchData.getMon1_w())?0f:Float.parseFloat(cinchData.getMon1_w()));
        weights.add(TextUtils.isEmpty(cinchData.getMon1_w())?0f:Float.parseFloat(cinchData.getMon1_w()));
        weights.add(TextUtils.isEmpty(cinchData.getMon2_w())?0f:Float.parseFloat(cinchData.getMon2_w()));
        weights.add(TextUtils.isEmpty(cinchData.getMon3_w())?0f:Float.parseFloat(cinchData.getMon3_w()));
        initLineChart(fats, weights);

    }

    private String getAnswer(String str) {
        if ("1".equals(cinchData.getAns1())) {
            return getString(R.string.often);
        } else if ("2".equals(cinchData.getAns1())) {
            return getString(R.string.sometime);
        } else if ("3".equals(cinchData.getAns1())) {
            return getString(R.string.little);
        } else {
            return getString(R.string.no_answer);
        }

    }
}
