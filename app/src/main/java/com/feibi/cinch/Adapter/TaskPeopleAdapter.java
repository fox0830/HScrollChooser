package com.feibi.cinch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.feibi.cinch.R;

import java.util.ArrayList;

public class TaskPeopleAdapter  extends RecyclerView.Adapter<TaskPeopleAdapter.ViewHolder> {
    Context context;
    ArrayList<String> pictures = new ArrayList<>();
    TaskPeopleAdapter.OnItemClickListener onItemClickListener;
    public TaskPeopleAdapter(Context context, ArrayList<String> pictures,OnItemClickListener onItemClickListener) {
        this.context = context;
        this.pictures = pictures;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TaskPeopleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_task_people, viewGroup, false);
        TaskPeopleAdapter.ViewHolder viewHolder = new TaskPeopleAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskPeopleAdapter.ViewHolder viewHolder, final int i) {
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onClick(i,view);
            }
        });
        viewHolder.tv_no.setText(""+(i+1));
        viewHolder.tv_user_name.setText("test");
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_no,tv_user_name;
        public ViewHolder(View view) {
            super(view);
            tv_no = view.findViewById(R.id.tv_no);
            tv_user_name = view.findViewById(R.id.tv_user_name);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos,View view);
    }

}
