package com.feibi.cinch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feibi.cinch.NetWork.respond.TaskData;
import com.feibi.cinch.R;

import java.util.ArrayList;

public class GropActAdapter  extends RecyclerView.Adapter<GropActAdapter.ViewHolder> {
    Context context;
    ArrayList<TaskData> datas = new ArrayList<>();
    OnItemClickListener onItemClickListener;
    public GropActAdapter(Context context, ArrayList<TaskData> datas,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.datas = datas;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public GropActAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_group_activity, viewGroup, false);
        GropActAdapter.ViewHolder viewHolder = new GropActAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GropActAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(i,view);
            }
        });
        viewHolder.tv_time.setText(datas.get(i).getTask_update());
        viewHolder.tv_time_area.setText(datas.get(i).getTask_date());
        viewHolder.tv_activity_name.setText(datas.get(i).getTask_name());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time,tv_time_area,tv_activity_name;

        public ViewHolder(View view) {
            super(view);
            tv_activity_name = view.findViewById(R.id.tv_activity_name);
            tv_time_area = view.findViewById(R.id.tv_time_area);
            tv_time = view.findViewById(R.id.tv_time);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos,View view);
    }

}
