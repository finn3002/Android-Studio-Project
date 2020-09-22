package com.team.team_project;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.lang.ref.Reference;
import java.sql.Timestamp;
import java.util.Calendar;

public class AddDetail extends AppCompatActivity {
    Button finish;
    EditText edfoodnm;
    EditText edprice;
    EditText edcar;
    EditText edpro;
    EditText edfat;
    EditText edCal;
    private FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_detail);
        findview();
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
                Timestamp d = new Timestamp(System.currentTimeMillis());
                bean.setKeyin(d);

                db.collection("personal").document("personTest")
                        .collection("allfood").document().set(bean,SetOptions.merge());
                Intent intent = new Intent(AddDetail.this,AddActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    private void findview(){
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
