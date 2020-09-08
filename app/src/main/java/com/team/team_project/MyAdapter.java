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
    public ArrayList<foodBean> foodBeans;
    public  RecyclerViewClickListener listener ;

    public MyAdapter(ArrayList<foodBean> foodBean , RecyclerViewClickListener listener) {
        this.foodBeans=foodBean;
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
        holder.foodNm.setText(foodBeans.get(position).getFoodnm().toString());
        holder.foodPrice.setText(String.valueOf(foodBeans.get(position).getFood_price()));
        holder.foodCal.setText(foodBeans.get(position).getFood_calorie().toString());
//        Log.e("test",foodBeans.get(position).getFoodnm());

    }
    @Override
    public int getItemCount() {
        return foodBeans.size();
    }
    public interface RecyclerViewClickListener{
        void  onClick(View v ,int position);
    }


}

