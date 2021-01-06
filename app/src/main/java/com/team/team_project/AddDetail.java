package com.team.team_project;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.ref.Reference;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AddDetail extends AppCompatActivity {
    public static final int pic_id = 123;
    Button finish,changephoto;
    EditText edfoodnm;
    EditText edprice;
    EditText edcar;
    EditText edpro;
    EditText edfat;
    EditText edCal;
    public  ImageView yaki;
    private FirebaseFirestore db ;
    String results;
    int visible=0;
    public FirebaseStorage image_db;
    public Bitmap bitmap2;


    int PICK_IMAGE_REQUEST = 111;
    Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_detail);
        findview();
        yaki.findViewById(R.id.yaki);
        image_db= FirebaseStorage.getInstance("gs://sprojct-f638d.appspot.com/");
        StorageReference mStorageRef = image_db.getReference();

        final Intent intent=getIntent();
        Bundle b=intent.getExtras();
        final Bitmap bmp=(Bitmap) b.getParcelable("bitmap");
//        bitmap2=bmp;
        yaki.setImageBitmap(bmp);
        visible=b.getInt("visible");
        if(visible==1){
//            if(results!=null){
//                StorageReference childRef=mStorageRef.child(results+".jpg");
//                UploadTask uploadTask = childRef.putFile(filePath);
//                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                    }
//                });

//            }

            changephoto.setVisibility(View.INVISIBLE);
            results=b.getString("results");
            switch (results){
                case "hotdog":
                    edfoodnm.setText("熱狗");
                    edCal.setText("289.7");
                    edcar.setText("61");
                    edpro.setText("9");
                    edfat.setText("8.2");
                    break;
                case "cake":
                    edfoodnm.setText("蛋糕");
                    edCal.setText("595");
                    edcar.setText("80");
                    edpro.setText("7");
                    edfat.setText("6.4");
                    break;
                case "banana":
                    edfoodnm.setText("香蕉");
                    edCal.setText("200");
                    edcar.setText("44");
                    edpro.setText("24");
                    edfat.setText("6");
                    break;
                default:
                    edfoodnm.setText("未知");
                    edCal.setText("");
                    edcar.setText("");
                    edpro.setText("");
                    edfat.setText("");
            }

        }


        changephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//            yaki.setImageResource(R.drawable.yaki);

                Intent intent2=new Intent(AddDetail.this,MainActivityy.class);
                startActivity(intent2);

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

                bean.setKeyin("2021/01/05");
                System.out.println(dateData);

                    db.collection("personal").document("personTest") //加入新食物
                            .collection("allfood").document().set(bean,SetOptions.merge());
                    Log.e("qwerqwe",bean.toString());

//                Bundle bundle=new Bundle();
//                bundle.putParcelable("bitmap",bmp);
                Intent intent = new Intent(AddDetail.this,AddActivity.class);
//                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });
        visible=0;
    }
//    public void showdialog(View v){
//        AlertDialog.Builder dialog = new AlertDialog.Builder(AddDetail.this);
//        final String[] food = {"粄條","烏龍麵","炒烏龍麵","炒麵","意麵"};
//        dialog.setTitle("請選擇符合項目");
//        dialog.setItems(food, new DialogInterface.OnClickListener(){
//            @Override
//
//            public void onClick(DialogInterface dialog, int which) {
//                edfoodnm.setText("炒烏龍麵");
//                edCal.setText("354");
//                edcar.setText("61");
//                edpro.setText("9");
//                edfat.setText("8.2");
//            }
//        });
//        dialog.show();

//    }
//@Override
//protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//
//    if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//        filePath = data.getData();
//
//        try {
//            //getting image from gallery
////            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//            Bitmap bitmap = bitmap2;
//
//            //Setting image to ImageView
////            imgView.setImageBitmap(bitmap);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    }

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
//    protected void onActivityResult(int requestCode,
//                                    int resultCode,
//                                    Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == pic_id) {
//
//            Bitmap photo = (Bitmap) data.getExtras()
//                    .get("data");
//
//            yaki.setImageBitmap(photo);
//        }
//    }

}
