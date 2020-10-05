package com.team.team_project;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.ref.Reference;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddDetail extends AppCompatActivity {
    Button finish,changephoto;
    EditText edfoodnm;
    EditText edprice;
    EditText edcar;
    EditText edpro;
    EditText edfat;
    EditText edCal;
    ImageView yaki;
    private FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_detail);
        findview();


        changephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            yaki.findViewById(R.id.yaki);
            yaki.setImageResource(R.drawable.yaki);
                changephoto.setVisibility(View.INVISIBLE);


            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                foodBean bean=new foodBean();
                bean.setFoodnm(edfoodnm.getText().toString());
                bean.setFood_price(Integer.valueOf(edprice.getText().toString()));
                bean.setFood_calorie(Double.valueOf(edCal.getText().toString()));
                bean.setFood_protein(Double.valueOf(edpro.getText().toString()));
                bean.setFood_fat(Double.valueOf(edfat.getText().toString()));
                bean.setFood_carbon(Double.valueOf(edcar.getText().toString()));
//                Timestamp d = new Timestamp(System.currentTimeMillis());
//                List<String> date= Arrays.asList(d.toString().split(" "));
//                bean.setKeyin(date.get(0).toString());
                String dateData=getIntent().getExtras().getString("dateData");

                bean.setKeyin(dateData);

                    db.collection("personal").document("personTest") //加入新食物
                            .collection("allfood").document().set(bean,SetOptions.merge());


                Intent intent = new Intent(AddDetail.this,AddActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    public void showdialog(View v){
        AlertDialog.Builder dialog = new AlertDialog.Builder(AddDetail.this);
        final String[] food = {"粄條","烏龍麵","炒烏龍麵","炒麵","意麵"};
        dialog.setTitle("請選擇符合項目");
        dialog.setItems(food, new DialogInterface.OnClickListener(){
            @Override

            public void onClick(DialogInterface dialog, int which) {
                edfoodnm.setText("炒烏龍麵");
                edCal.setText("354");
                edcar.setText("61");
                edpro.setText("9");
                edfat.setText("8.2");
            }
        });
        dialog.show();


    }
    private void findview(){
        changephoto=findViewById(R.id.addphoto);
        yaki=findViewById(R.id.yaki);
        finish = findViewById(R.id.finish);
        edfoodnm=findViewById(R.id.edFoodnm);
        edprice=findViewById(R.id.edPrice);
        edcar=findViewById(R.id.edCar);
        edpro=findViewById(R.id.edPro);
        edfat=findViewById(R.id.edFat);
        edCal=findViewById(R.id.edCal);
        db = FirebaseFirestore.getInstance();
    }



}
