package com.team.team_project;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.net.URL;

//食物詳細資訊View
public class FoodProfile extends AppCompatActivity {
    Button cancel;
    TextView fooddetailname , fooddetaildollor , fooddetailcal , fooddetailcarbon,fooddetailportine,fooddetailfat;
    String foodname , fooddollar , foodcal,foodcarbon,foodportine,foodfat;
    private FirebaseStorage image_db;
    ImageView editImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cake);

        findview();
        image_db= FirebaseStorage.getInstance("gs://sprojct-f638d.appspot.com/");
        StorageReference mStorageRef = image_db.getReference();
        StorageReference getRef=mStorageRef.child("woodon.jpg");
        Task<Uri> url=getRef.getDownloadUrl();
        try {
            final File tmpFile = File.createTempFile("img", "jpeg");
            StorageReference reference = getRef;

            //  "id" is name of the image file....

            reference.getFile(tmpFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                    Bitmap image = BitmapFactory.decodeFile(tmpFile.getAbsolutePath());
                    editImage.setMaxHeight(300);
                    editImage.setImageBitmap(image);


                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


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


    }
    private void findview(){
        fooddetailname = findViewById(R.id.FoodDetailName);
        fooddetaildollor = findViewById(R.id.FoodDetailDollar);
        fooddetailcal = findViewById(R.id.FoodDetailCal);
        fooddetailcarbon = findViewById(R.id.FoodDetailCarbon);
        fooddetailportine = findViewById(R.id.FoodDetailPortine);
        fooddetailfat = findViewById(R.id.FoodDetailFat);
        editImage=findViewById(R.id.editImage);


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
