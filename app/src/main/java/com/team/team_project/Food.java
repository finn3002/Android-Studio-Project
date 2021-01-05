package com.team.team_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

public class Food extends AppCompatActivity {
    public static final int pic_id = 123;


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
        selfphoto = (ImageView)findViewById(R.id.imageView10);
        selfdetailclick.setOnClickListener(selfdetail);
        addphoto=findViewById(R.id.addphoto);
        addphoto.setOnClickListener(clickaddphoto);

    }
    public View.OnClickListener clickaddphoto = new View.OnClickListener(){
        public void onClick(View v){
//            selfphoto=findViewById(R.id.imageView10);
//            selfphoto.setImageResource(R.drawable.kiki);
//            addphoto.setVisibility(View.INVISIBLE);
            Intent camera_intent
                    = new Intent(MediaStore
                    .ACTION_IMAGE_CAPTURE);

            // Start the activity with camera_intent,
            // and request pic id
            startActivityForResult(camera_intent, pic_id);
        }
    };



    public View.OnClickListener selfdetail = new View.OnClickListener() {

        public void onClick(View v) {
            selfresult= (TextView)findViewById(R.id.resulttitle);
            selfinside= (TextView)findViewById(R.id.selfinside);
            foodname=findViewById(R.id.foodname);
            chicken=findViewById(R.id.chicken);
        selfresult.setText("分析結果：");
        selfinside.setText("    情緒：心情愉悅　\n　攝取：蛋白質攝取量偏低。");
        foodname.setText("推薦：雞肉飯");
        chicken.setImageResource( R.drawable.chickenla);


        }
    };
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {

        // Match the request 'pic id with requestCode
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {

            // BitMap is data structure of image file
            // which stor the image in memory
            Bitmap photo = (Bitmap) data.getExtras()
                    .get("data");

            // Set the image in imageview for display
            selfphoto.setImageBitmap(photo);
        }
    }
}



