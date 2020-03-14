package com.feibi.cinch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.feibi.cinch.R;
import com.feibi.cinch.utils.MeasureUtils;

import java.util.ArrayList;

public class ScaleAdapter extends RecyclerView.Adapter<ScaleAdapter.ViewHolder> {
    Context context;
    ArrayList<String> datas = new ArrayList<>();

    public ScaleAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public ScaleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_scale, viewGroup, false);
        ScaleAdapter.ViewHolder viewHolder = new ScaleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScaleAdapter.ViewHolder viewHolder, final int i) {
        if (i == 0 || i == datas.size() + 1) {
            RecyclerView.LayoutParams linearParams = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams(); //取控件textView当前的布局参数
            linearParams.width = MeasureUtils.getWidth(context) / 2 - MeasureUtils.dip2px(context, 4);
            viewHolder.itemView.setLayoutParams(linearParams);
            viewHolder.v_min_scale.setVisibility(View.INVISIBLE);
            viewHolder.v_scale.setVisibility(View.INVISIBLE);
            viewHolder.tv_scale_value.setVisibility(View.GONE);
        } else {
            RecyclerView.LayoutParams linearParams = (RecyclerView.LayoutParams) viewHolder.itemView.getLayoutParams(); //取控件textView当前的布局参数
            linearParams.width = MeasureUtils.dip2px(context, 8);
            viewHolder.itemView.setLayoutParams(linearParams);
            viewHolder.v_min_scale.setVisibility(View.VISIBLE);
            viewHolder.v_scale.setVisibility(View.VISIBLE);
            viewHolder.tv_scale_value.setText(datas.get(i-1));
            if ((i - 1) % 5 == 0) {
                viewHolder.tv_scale_value.setVisibility(View.VISIBLE);
                viewHolder.v_scale.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_scale_value.setVisibility(View.GONE);
                viewHolder.v_scale.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return datas.size() + 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_scale_value;
        View v_scale, v_min_scale;

        public ViewHolder(View view) {
            super(view);
            tv_scale_value = view.findViewById(R.id.tv_scale_value);
            v_scale = view.findViewById(R.id.v_scale);
            v_min_scale = view.findViewById(R.id.v_min_scale);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos, View view);
    }

}
