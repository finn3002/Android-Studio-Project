package com.team.team_project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;
import static androidx.core.content.ContextCompat.startForegroundService;


class MyAdapter2 extends RecyclerView.Adapter <MyAdapter2.PagerViewHolder>{
//    public ArrayList<foodBean> foodBeans;
    public  ArrayList<Mydata> mydatas;
    public  RecyclerViewClickListener listener ;

    public MyAdapter2(ArrayList<Mydata> mydata ) {
//        this.foodBeans=foodBean;
        this.mydatas=mydata;
//        this.listener = listener;
    }
    public class PagerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
//        private TextView foodNm,foodPrice,foodCal;
        private TextView totalresult,remainingresult,moneyresult;
        private Button addbutton;

        public PagerViewHolder(View v){
            super(v);

            //MyAdapter.foodNm,MyAdapter.foodPrice,MyAdapter.foodCal
//            foodNm=v.findViewById(R.id.foodNm);
//            foodPrice=v.findViewById(R.id.foodPrice);
//            foodCal= v.findViewById(R.id.foodCal);
            totalresult=v.findViewById(R.id.totalresult);
            remainingresult=v.findViewById(R.id.remainingresult);
            moneyresult=v.findViewById(R.id.moneyresult);
            addbutton=v.findViewById(R.id.additem);
            addbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                }
            });
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
//        listener.onClick(v, getAdapterPosition());
        }

    }

    @Override
    public MyAdapter2.PagerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_page,parent,false);
        PagerViewHolder viewHolder=new PagerViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MyAdapter2.PagerViewHolder holder, int position) {
//        holder.foodNm.setText(foodBeans.get(position).getFoodnm().toString());
//        holder.foodPrice.setText(String.valueOf(foodBeans.get(position).getFood_price()));
//        holder.foodCal.setText(foodBeans.get(position).getFood_calorie().toString());

        holder.totalresult.setText(String.valueOf(mydatas.get(position).getTotalresult()));
        holder.remainingresult.setText(String.valueOf(mydatas.get(position).getRemainingresult()));
        holder.moneyresult.setText(String.valueOf(mydatas.get(position).getMoneyresult()));

        Log.e("testdata",String.valueOf(mydatas.get(position).getTotalresult()));

//        Log.e("test",foodBeans.get(position).getFoodnm());

    }
    @Override
    public int getItemCount() {
        return mydatas.size();
    }
    public interface RecyclerViewClickListener{
        void  onClick(View v, int position);
    }


}

