package com.feibi.cinch.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.angel.view.SWImageView;
import com.bumptech.glide.Glide;
import com.feibi.cinch.R;

import java.util.ArrayList;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    Context context;
    ArrayList<String> datas = new ArrayList<>();
    ArrayList<String> hasSelectPictures = new ArrayList<>();

    public PictureAdapter(Context context, ArrayList<String> datas, ArrayList<String> hasSelectPictures) {
        this.context = context;
        this.datas = datas;
        this.hasSelectPictures = hasSelectPictures;
    }

    @NonNull
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, viewGroup, false);
        PictureAdapter.ViewHolder viewHolder = new PictureAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PictureAdapter.ViewHolder viewHolder, final int i) {
//        Glide.with(context).load(datas.get(i).getPath()).into(viewHolder.iv_pic);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        SWImageView iv_pic;
        LinearLayout ll_check;
        ImageView iv_check;

        public ViewHolder(View view) {
            super(view);
//            iv_pic = view.findViewById(R.id.iv_pic);
//            ll_check = view.findViewById(R.id.ll_check);
//            iv_check = view.findViewById(R.id.iv_check);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

}
