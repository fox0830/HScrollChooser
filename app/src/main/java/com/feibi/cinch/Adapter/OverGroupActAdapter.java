package com.feibi.cinch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feibi.cinch.R;

import java.util.ArrayList;

public class OverGroupActAdapter extends RecyclerView.Adapter<OverGroupActAdapter.ViewHolder> {
    Context context;
    ArrayList<String> pictures = new ArrayList<>();
    OverGroupActAdapter.OnItemClickListener onItemClickListener;

    public OverGroupActAdapter(Context context, ArrayList<String> pictures) {
        this.context = context;
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public OverGroupActAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_over_group_activity, viewGroup, false);
        OverGroupActAdapter.ViewHolder viewHolder = new OverGroupActAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OverGroupActAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.rv_ranking.getVisibility()==View.VISIBLE){
                    viewHolder.rv_ranking.setVisibility(View.GONE);
                    viewHolder.iv_arrow.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_down));
                }else {
                    viewHolder.rv_ranking.setVisibility(View.VISIBLE);
                    viewHolder.iv_arrow.setImageDrawable(context.getDrawable(R.drawable.ic_arrow_up));
                }
            }
        });
        ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        RankAdapter rankAdapter = new RankAdapter(context,data);
        viewHolder.rv_ranking.setLayoutManager(new LinearLayoutManager(context));
        viewHolder.rv_ranking.setAdapter(rankAdapter);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_task_name;
        ImageView iv_arrow;
        RecyclerView rv_ranking;
        public ViewHolder(View view) {
            super(view);
            tv_task_name = view.findViewById(R.id.tv_task_name);
            iv_arrow = view.findViewById(R.id.iv_arrow);
            rv_ranking = view.findViewById(R.id.rv_ranking);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos, View view);
    }

}
