package com.team.team_project;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.text.DecimalFormat;
import java.util.ArrayList;
public class AddActivity extends Activity {
    Button tablebutton,foodbutton,profilebutton,chatbutton,addbutton,cakedetail,cheesedetail;
    TextView remainingresult,totalmoney,totalresult;
    //ListView
    private MyAdapter adapter;
    private ArrayList<foodSet> foodsets= new ArrayList<>();
    private RecyclerView recyclerView;
    private GlobalV gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DecimalFormat nf = new DecimalFormat("0");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
         gv= (GlobalV) getApplication();

        tablebutton  = findViewById(R.id.table);
        tablebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Analysis.class);
                startActivity(intent);
            }
        });
        foodbutton  = findViewById(R.id.recommend);
        foodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Food.class);
                startActivity(intent);
            }
        });
        profilebutton  = findViewById(R.id.profile);
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,PageActivity.class);
                startActivity(intent);
            }
        });
        chatbutton = findViewById(R.id.chat);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Chat.class);
                startActivity(intent);
            }
        });
        addbutton = findViewById(R.id.additem);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,AddDetail.class);
                startActivity(intent);
            }
        });
        remainingresult = findViewById(R.id.remainingresult);
        totalmoney=findViewById(R.id.moneyresult);
        totalresult=findViewById(R.id.totalresult);
        gv.setDollar(160);
        gv.setAddcal(730.0);
        gv.setCal((gv.getCal()-gv.getAddcal()));
        remainingresult.setText((nf.format( gv.getCal())));
        totalmoney.setText((nf.format( gv.getDollar())));
        totalresult.setText((nf.format( gv.getAddcal())));

    //recyclerView Set
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //設定分割線
        recyclerView.setLayoutManager(linearLayoutManager);

        //設定 LayoutManager
        foodsets.add(new foodSet("藍莓蛋糕",125,350.0));
        foodsets.add(new foodSet("起司蛋餅",35,380.0));
        adapter= new MyAdapter(foodsets);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        //蛋糕詳細資料
        cakedetail  = findViewById(R.id.cakedetail);
        cakedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Cake.class);
                startActivity(intent);
            }
        });
        //起司蛋餅詳細資料
        cheesedetail  = findViewById(R.id.cheesedetail);
        cheesedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Cheese.class);
                startActivity(intent);
            }
        });



    }
}
