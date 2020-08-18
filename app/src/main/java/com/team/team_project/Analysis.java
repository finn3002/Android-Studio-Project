package com.team.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Analysis extends AppCompatActivity {
    Button addbutton;
    Button profilebutton;
    Button foodbutton;
    Button chatbutton;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        addbutton  = findViewById(R.id.pencil);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Analysis.this,AddActivity.class);
                startActivity(intent);
            }
        });

        profilebutton  = findViewById(R.id.profile);
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Analysis.this,PageActivity.class);
                startActivity(intent);
            }
        });
        foodbutton = findViewById(R.id.recommend);
        foodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Analysis.this,Food.class);
                startActivity(intent);
            }
        });
       chatbutton = findViewById(R.id.chat);
     chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Analysis.this,Chat.class);
                startActivity(intent);
            }
        });
        pieChart =(PieChart)findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.7f);
        pieChart.setCenterText("今日\n營養攝取");
        pieChart.setCenterTextSize(25);
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(android.R.color.white);
        pieChart.setTransparentCircleAlpha(80);
        pieChart.setTransparentCircleRadius(58f);

        ArrayList<PieEntry> Values = new ArrayList<>();
        Values.add(new PieEntry(34,"碳水化合物"));
        Values.add(new PieEntry(23,"蛋白質"));
        Values.add(new PieEntry(14,"脂肪"));
        Values.add(new PieEntry(35,"鈉"));
        Values.add(new PieEntry(23,"糖"));

        pieChart.animateY(2000, Easing.EaseInOutCubic);
        PieDataSet dataSet = new PieDataSet(Values,"");
        dataSet.setSliceSpace(3f);
        dataSet.getSelectionShift();
        dataSet.setValueTextSize(20f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data  = new PieData((dataSet));
        data.setValueTextSize(20f);
        data.setValueTextColor(Color.WHITE);
        pieChart.setData(data);



    }
    }

