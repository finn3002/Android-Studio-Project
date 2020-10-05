package com.team.team_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Food extends AppCompatActivity {
    Button addbutton,addphoto;
    Button tablebutton;
    Button profilebutton;
    Button chatbuttom;
    Button selfdetailclick;
    TextView selfresult, selfinside,foodname;
    ImageView chicken,selfphoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        addbutton = findViewById(R.id.pencil);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Food.this, AddActivity.class);
                startActivity(intent);
            }
        });
        tablebutton = findViewById(R.id.table);
        tablebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Food.this, Analysis.class);
                startActivity(intent);
            }
        });

        profilebutton = findViewById(R.id.profile);
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Food.this, PageActivity.class);
                startActivity(intent);
            }
        });
        chatbuttom = findViewById(R.id.chat);
        chatbuttom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Food.this, Chat.class);
                startActivity(intent);
            }
        });

        selfdetailclick = (Button) findViewById(R.id.selfclick);
        selfdetailclick.setOnClickListener(selfdetail);
        addphoto=findViewById(R.id.addphoto);
        addphoto.setOnClickListener(clickaddphoto);

    }
    public View.OnClickListener clickaddphoto = new View.OnClickListener(){
        public void onClick(View v){
            selfphoto=findViewById(R.id.detailphoto);
            selfphoto.setImageResource(R.drawable.userpic);
            addphoto.setVisibility(View.INVISIBLE);
        }
    };

    public View.OnClickListener selfdetail = new View.OnClickListener() {

        public void onClick(View v) {
            selfresult= (TextView)findViewById(R.id.resulttitle);
            selfinside= (TextView)findViewById(R.id.selfinside);
            foodname=findViewById(R.id.foodname);
            chicken=findViewById(R.id.chicken);
        selfresult.setText("分析結果：");
        selfinside.setText("    根據個人化分析，\n    心情愉悅、蛋白質攝取量偏低。");
        foodname.setText("推薦：雞肉飯");
        chicken.setImageResource( R.drawable.chickenla);


        }
    };
}



