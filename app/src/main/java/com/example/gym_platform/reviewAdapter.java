package com.example.gym_platform;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;


import java.util.ArrayList;

public class reviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPicture;
        TextView tvName;
        TextView tvText;
        TextView tvDate;

        MyViewHolder(View view) {
            super(view);
            ivPicture = view.findViewById(R.id.reviewUserProfile);
            tvName = view.findViewById(R.id.reviewUserName);
            tvText = view.findViewById(R.id.reviewText);
            tvDate = view.findViewById(R.id.reviewDate);
        }
    }

    private ArrayList<reviewItem> reviewItemArrayList;

    reviewAdapter(ArrayList<reviewItem> reviewItemArrayList) {
        this.reviewItemArrayList = reviewItemArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_review, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.ivPicture.setImageResource(reviewItemArrayList.get(position).drawableId);
        myViewHolder.tvName.setText(reviewItemArrayList.get(position).username);
        myViewHolder.tvText.setText(reviewItemArrayList.get(position).reviewtext);
        myViewHolder.tvDate.setText(reviewItemArrayList.get(position).date);
    }

    @Override
    public int getItemCount() {
        return reviewItemArrayList.size();
    }
}
