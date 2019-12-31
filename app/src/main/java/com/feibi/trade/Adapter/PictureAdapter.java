package com.feibi.trade.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.feibi.trade.R;

import java.util.ArrayList;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {
    Context context;
    ArrayList<Picture> pictures = new ArrayList<>();

    public PictureAdapter(Context context, ArrayList<Picture> pictures) {
        this.context = context;
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_picture, viewGroup, false);
        PictureAdapter.ViewHolder viewHolder = new PictureAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PictureAdapter.ViewHolder viewHolder, final int i) {
        Glide.with(context).load(pictures.get(i).getPath()).into(viewHolder.iv_pic);
        if (pictures.get(i).isChoose()) {
            viewHolder.ll_check.setBackground(context.getDrawable(R.drawable.is_check));
            viewHolder.iv_check.setVisibility(View.VISIBLE);
        } else {
            viewHolder.ll_check.setBackground(context.getDrawable(R.drawable.no_check));
            viewHolder.iv_check.setVisibility(View.INVISIBLE);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pictures.get(i).switchChoose();
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public ArrayList<Picture> getChoosePictures() {
        ArrayList<Picture> choosePictures = new ArrayList<>();
        for(Picture picture:pictures){
            if(picture.isChoose()){
                choosePictures.add(picture);
            }
        }
        return choosePictures;
    }
    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_pic;
        LinearLayout ll_check;
        ImageView iv_check;

        public ViewHolder(View view) {
            super(view);
            iv_pic = view.findViewById(R.id.iv_pic);
            ll_check = view.findViewById(R.id.ll_check);
            iv_check = view.findViewById(R.id.iv_check);
        }
    }

    public interface OnItemClickListener {
        void onClick(int pos);
    }

}
