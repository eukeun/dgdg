package com.example.gym_platform;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class membershipAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle1;
        TextView tvTitle2;
        TextView tvPrice;

        MyViewHolder(View view) {
            super(view);
            tvTitle1 = view.findViewById(R.id.membership_title1);
            tvTitle2 = view.findViewById(R.id.membership_title2);
            tvPrice = view.findViewById(R.id.membership_price);
        }
    }

    private ArrayList<membershipItem> membershipItemArrayList;
    membershipAdapter(ArrayList<membershipItem> membershipItemArrayList) {
        this.membershipItemArrayList = membershipItemArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_membership, parent, false);
        return new membershipAdapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        membershipAdapter.MyViewHolder myViewHolder = (membershipAdapter.MyViewHolder) holder;
        myViewHolder.tvTitle1.setText(membershipItemArrayList.get(position).membership_title1);
        myViewHolder.tvTitle2.setText(membershipItemArrayList.get(position).membership_title2);
        myViewHolder.tvPrice.setText(membershipItemArrayList.get(position).price);
    }

    @Override
    public int getItemCount() {
        return membershipItemArrayList.size();
    }
}
