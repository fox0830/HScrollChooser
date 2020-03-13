package com.feibi.cinch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feibi.cinch.R;

import java.util.ArrayList;

public class JoinTaskAdapter   extends RecyclerView.Adapter<JoinTaskAdapter.ViewHolder> {
    Context context;
    ArrayList<String> datas = new ArrayList<>();
    JoinTaskAdapter.OnItemClickListener onItemClickListener;
    public JoinTaskAdapter(Context context, ArrayList<String> datas, JoinTaskAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.datas = datas;
        this.onItemClickListener=onItemClickListener;
    }

    @NonNull
    @Override
    public JoinTaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_join_task, viewGroup, false);
        JoinTaskAdapter.ViewHolder viewHolder = new JoinTaskAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull JoinTaskAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_task_name,tv_join_time;

        public ViewHolder(View view) {
            super(view);
            tv_task_name = view.findViewById(R.id.tv_task_name);
            tv_join_time = view.findViewById(R.id.tv_join_time);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

}
