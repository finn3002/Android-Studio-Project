package com.team.team_project;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//食物詳細資訊View
public class FoodProfile extends AppCompatActivity {
    Button cancel;
    TextView fooddetailname , fooddetaildollor , fooddetailcal , fooddetailcarbon,fooddetailportine,fooddetailfat;
    String foodname , fooddollar , foodcal,foodcarbon,foodportine,foodfat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake);
        fooddetailname = findViewById(R.id.FoodDetailName);
        fooddetaildollor = findViewById(R.id.FoodDetailDollar);
        fooddetailcal = findViewById(R.id.FoodDetailCal);
        fooddetailcarbon = findViewById(R.id.FoodDetailCarbon);
        fooddetailportine = findViewById(R.id.FoodDetailPortine);
        fooddetailfat = findViewById(R.id.FoodDetailFat);

        Bundle extras =getIntent().getExtras();

        if(extras!=null){
            foodname = extras.getString("name");
            fooddollar = extras.getString("price");
            foodcal = extras.getString("cal");
            foodcarbon = extras.getString("carbon");
            foodportine = extras.getString("protein");
            foodfat = extras.getString("fat");
        }
        fooddetailname.setText(foodname);
        fooddetaildollor.setText(fooddollar);
        fooddetailcal.setText(foodcal);
        fooddetailcarbon.setText(foodcarbon);
        fooddetailportine.setText(foodportine);
        fooddetailfat.setText(foodfat);


        cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodProfile.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }
}
