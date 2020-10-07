package com.team.team_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Field;

public class Chat extends AppCompatActivity {
    Button addbutton, profilebutton, foodbutton, tablebutton;
    androidx.appcompat.widget.Toolbar toolbar;
    TextView finn, sherry;

    public Chat() throws NoSuchFieldException, IllegalAccessException {
    }

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
        finn = findViewById(R.id.textView5);
        sherry = findViewById(R.id.textView6);


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void showfinn(View v) {
        AlertDialog builder = new AlertDialog.Builder(Chat.this)
                .setTitle("阿涵")
                .setMessage("今日食用項目：\n水果沙拉\n無糖綠茶\n牛肉麵\n芒果綠茶")
                .setIcon(R.drawable.friendpi)
                .show();
        try {
            //獲取mAlert對象
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(builder);

            //獲取mTitleView並設置大小
            Field mTitle = mAlertController.getClass().getDeclaredField("mTitleView");
            mTitle.setAccessible(true);
            TextView mTitleView = (TextView) mTitle.get(mAlertController);
            mTitleView.setTextSize(40);


            //獲取mMessageView並設置大小
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextSize(30);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public void showsherry(View v) throws NoSuchFieldException {
        AlertDialog builder = new AlertDialog.Builder(Chat.this)
                .setTitle("Sherry")
                .setMessage("今日食用項目：\n起司蛋餅\n溫紅茶\n薑汁燒肉磚壓吐司\n水果茶")

                .setIcon(R.drawable.friendpic2)
                .show();


        try {
            //獲取mAlert對象
            Field mAlert = AlertDialog.class.getDeclaredField("mAlert");
            mAlert.setAccessible(true);
            Object mAlertController = mAlert.get(builder);

            //獲取mTitleView並設置大小
            Field mTitle = mAlertController.getClass().getDeclaredField("mTitleView");
            mTitle.setAccessible(true);
            TextView mTitleView = (TextView) mTitle.get(mAlertController);
            mTitleView.setTextSize(40);

            //獲取mMessageView並設置大小
            Field mMessage = mAlertController.getClass().getDeclaredField("mMessageView");
            mMessage.setAccessible(true);
            TextView mMessageView = (TextView) mMessage.get(mAlertController);
            mMessageView.setTextSize(30);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }




        }



