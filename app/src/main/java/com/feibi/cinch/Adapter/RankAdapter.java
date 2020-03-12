package com.feibi.cinch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feibi.cinch.R;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    Context context;
    ArrayList<String> pictures = new ArrayList<>();
    RankAdapter.OnItemClickListener onItemClickListener;
    public RankAdapter(Context context, ArrayList<String> pictures) {
        this.context = context;
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_ranking, viewGroup, false);
        RankAdapter.ViewHolder viewHolder = new RankAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RankAdapter.ViewHolder viewHolder, final int i) {
            if(i==0){
                viewHolder.iv_rank.setImageDrawable(context.getDrawable(R.mipmap.no_1));
            }else if(i==1){
                viewHolder.iv_rank.setImageDrawable(context.getDrawable(R.mipmap.no_2));
            }else if(i==2){
                viewHolder.iv_rank.setImageDrawable(context.getDrawable(R.mipmap.no_3));
            }
            if(i<3){
                viewHolder.iv_rank.setVisibility(View.VISIBLE);
                viewHolder.tv_rank.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.iv_rank.setVisibility(View.INVISIBLE);
                viewHolder.tv_rank.setVisibility(View.VISIBLE);
                viewHolder.tv_rank.setText((i+1)+"");
            }
            viewHolder.tv_rank_txt.setText("第"+(i+1)+"名");
            viewHolder.tv_user_name.setText("test");
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_rank_txt,tv_rank,tv_user_name;
        ImageView iv_rank;
        public ViewHolder(View view) {
            super(view);
            tv_rank_txt = view.findViewById(R.id.tv_rank_txt);
            tv_rank = view.findViewById(R.id.tv_rank);
            iv_rank = view.findViewById(R.id.iv_rank);
            tv_user_name = view.findViewById(R.id.tv_user_name);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos,View view);
    }

}