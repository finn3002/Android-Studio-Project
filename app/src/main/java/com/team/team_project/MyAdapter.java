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


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView now;
        private TextView rest;
        public ViewHolder(View v){
            super(v);
            //MyAdapter.foodNm,MyAdapter.foodPrice,MyAdapter.foodCal
            now=v.findViewById(R.id.foodNm);
            rest=v.findViewById(R.id.foodPrice);


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

        holder.now.setText(foodsets.get(position).getFoodNm().toString());
        holder.rest.setText(String.valueOf(foodsets.get(position).getFoodPrice()));
//        holder.foodCal.setText(foodsets.get(position).getFoodCal().toString());
        Log.e("test",foodsets.get(position).getFoodNm());

    }

    @Override
    public int getItemCount() {
        return foodsets.size();
    }



}

