package com.team.team_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AlertDialogLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{
    public ArrayList<foodBean> foodBeans;
    public  RecyclerViewClickListener listener ;
    public FirebaseFirestore db;


    public MyAdapter(ArrayList<foodBean> foodBean , RecyclerViewClickListener listener) {
        this.foodBeans=foodBean;
        this.listener = listener;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView foodNm,foodPrice,foodCal;
        private Button delBt;


        public ViewHolder(View v){
            super(v);
            //MyAdapter.foodNm,MyAdapter.foodPrice,MyAdapter.foodCal
            foodNm=v.findViewById(R.id.foodNm);
            foodPrice=v.findViewById(R.id.foodPrice);
            foodCal= v.findViewById(R.id.foodCal);
            delBt=v.findViewById(R.id.delBt);
            db=FirebaseFirestore.getInstance();

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
        listener.onClick(v, getAdapterPosition());
        }
    }

    public void removeItem(int position){

//       db.collection("personal").document("personTest") //刪除食物
//       .collection("allfood").whereEqualTo("foodnm",foodBeans.get(position).getFoodnm()).get()
//               .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                   @Override
//                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                       if(task.isSuccessful()){
//                           for(QueryDocumentSnapshot document : task.getResult()){
//                               String delId=document.getId();
//                               db.collection("personal").document("personTest") //刪除食物
//                                       .collection("allfood").document(delId).delete();
//
//                           }
//                       }
//                   }
//               });

        foodBeans.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_row,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final MyAdapter.ViewHolder holder, final int position) {
        holder.foodNm.setText(foodBeans.get(position).getFoodnm().toString());
        holder.foodPrice.setText(String.valueOf(foodBeans.get(position).getFood_price()));
        holder.foodCal.setText(foodBeans.get(position).getFood_calorie().toString());
        holder.delBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog builder= new AlertDialog.Builder(v.getContext())
                       .setNegativeButton("取消",null)
                       .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeItem(position);
                    }
                }).setMessage("確定要刪除"+foodBeans.get(position).getFoodnm()+"?").show();
                builder.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.rgb(12,28,37));
                builder.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.rgb(12,28,37));


            }
        });


    }
    @Override
    public int getItemCount() {
        return foodBeans.size();
    }
    public interface RecyclerViewClickListener{
        void  onClick(View v ,int position);
    }


}

