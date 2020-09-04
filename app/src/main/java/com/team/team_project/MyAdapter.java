package com.team.team_project;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{
    public ArrayList<foodSet> foodsets;
    public  RecyclerViewClickListener listener ;

    public MyAdapter(ArrayList<foodSet> foodset , RecyclerViewClickListener listener) {
        this.foodsets=foodset;
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView foodNm,foodPrice,foodCal;

        public ViewHolder(View v){
            super(v);
            //MyAdapter.foodNm,MyAdapter.foodPrice,MyAdapter.foodCal
            foodNm=v.findViewById(R.id.foodNm);
            foodPrice=v.findViewById(R.id.foodPrice);
            foodCal= v.findViewById(R.id.foodCal);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
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
    public interface RecyclerViewClickListener{
        void  onClick(View v ,int position);
    }


}

