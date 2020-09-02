package com.team.team_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {
    Button addbutton,profilebutton,foodbutton,tablebutton;
    androidx.appcompat.widget.Toolbar toolbar;
    BarChart barChart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        addbutton = findViewById(R.id.pencil);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, AddActivity.class);
                startActivity(intent);
            }
        });

        profilebutton = findViewById(R.id.profile);
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, PageActivity.class);
                startActivity(intent);
            }
        });
        foodbutton = findViewById(R.id.recommend);
        foodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, Food.class);
                startActivity(intent);
            }
        });
        tablebutton = findViewById(R.id.table);
        tablebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, Analysis.class);
                startActivity(intent);
            }
        });
//        barChart = findViewById(R.id.barchart);
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(32f,0));
//        entries.add(new BarEntry(2f,1));
//
//        BarDataSet bardataset = new BarDataSet(entries, "Cells");
//
//        ArrayList<String> labels = new ArrayList<String>();
//        labels.add("2016");
//        labels.add("2015");
//        labels.add("2014");
//        labels.add("2013");
//        labels.add("2012");
//        labels.add("2011");
//
//        BarData data = new BarData((IBarDataSet) labels,bardataset);
//        barChart.setData(data);
//        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
//        barChart.animateY(5000);

    }
    }


