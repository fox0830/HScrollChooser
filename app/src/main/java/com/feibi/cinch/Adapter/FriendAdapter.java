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

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {
    Context context;
    ArrayList<String> pictures = new ArrayList<>();
    FriendAdapter.OnItemClickListener onItemClickListener;

    public FriendAdapter(Context context, ArrayList<String> pictures, FriendAdapter.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.pictures = pictures;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_friend, viewGroup, false);
        FriendAdapter.ViewHolder viewHolder = new FriendAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(i,view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_user_name, tv_join_time;
        ImageView iv_user_head;

        public ViewHolder(View view) {
            super(view);
            tv_user_name = view.findViewById(R.id.tv_user_name);
            tv_join_time = view.findViewById(R.id.tv_join_time);
            iv_user_head = view.findViewById(R.id.iv_user_head);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos,View v);
    }

}
