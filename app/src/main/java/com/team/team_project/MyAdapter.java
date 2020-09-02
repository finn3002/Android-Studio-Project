package com.team.team_project;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{
    public ArrayList<foodSet> foodsets;
    public MyAdapter(ArrayList<foodSet> foodset) {
        foodsets=foodset;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView foodNm;
        private TextView foodPrice;
        private TextView foodCal;

        public ViewHolder(View v){
            super(v);
            //MyAdapter.foodNm,MyAdapter.foodPrice,MyAdapter.foodCal
            foodNm=v.findViewById(R.id.foodNm);
            foodPrice=v.findViewById(R.id.foodPrice);
            foodCal= v.findViewById(R.id.foodCal);


        }

    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( MyAdapter.ViewHolder holder, int position) {

        holder.foodNm.setText(foodsets.get(position).getFoodNm().toString());
        holder.foodPrice.setText(String.valueOf(foodsets.get(position).getFoodPrice()));
        holder.foodCal.setText(foodsets.get(position).getFoodCal().toString());
        Log.e("test",foodsets.get(position).getFoodNm());

    }

    @Override
    public int getItemCount() {
        return foodsets.size();
    }



}

