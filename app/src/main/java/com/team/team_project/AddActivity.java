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
import java.util.logging.Formatter;


public class AddActivity extends Activity {
    Button tablebutton;
    Button foodbutton;
    Button profilebutton;
    Button chatbutton;
    Button addbutton;
    TextView totalcar;
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
//        GV g= (GV) getApplicationContext();
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
//        test=20.0;
    totalcar = findViewById(R.id.totalresult);
//    gv.setCal(140.0);
    totalcar.setText((nf.format( gv.getCal())));

    //recyclerView Set
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //設定分割線
        recyclerView.setLayoutManager(linearLayoutManager); //設定 LayoutManager
        foodsets.add(new foodSet("蛋糕",1234,5.5));
        foodsets.add(new foodSet("蛋糕",1234,5.5));
        foodsets.add(new foodSet("蛋糕",1234,5.5));
        foodsets.add(new foodSet("蛋糕",1234,5.5));
        foodsets.add(new foodSet("蛋糕",1234,5.5));
        foodsets.add(new foodSet("蛋糕",1234,5.5));
        foodsets.add(new foodSet("蛋糕",1234,5.5));
        foodsets.add(new foodSet("蛋糕",1234,5.5));


        adapter= new MyAdapter(foodsets);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);



    }
}
