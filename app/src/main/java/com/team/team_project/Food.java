package com.team.team_project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Camera;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.TextView;

//import com.wonderkiln.camerakit.CameraKitImage;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Food extends AppCompatActivity {
    public static final int pic_id = 123;


    Button addbutton,addphoto;
    Button tablebutton;
    Button profilebutton;
    Button chatbuttom;
    Button selfdetailclick;
    TextView selfresult, selfinside,foodname;
    ImageView chicken,selfphoto;

    private static final int INPUT_SIZE = 224;
    private static final int IMAGE_MEAN = 117;
    private static final float IMAGE_STD = 1;
    private static final String INPUT_NAME = "input";
    private static final String OUTPUT_NAME = "output";
    public float cal=0;
    public float na=0;
    public float su=0;

    private static final String MODEL_FILE = "file:///android_asset/tensorflow_inception_graph.pb";
    private static final String LABEL_FILE =
            "file:///android_asset/imagenet_comp_graph_label_strings.txt";

    private com.mindorks.NutritionInformation.Classifier classifier;
    private Executor executor = Executors.newSingleThreadExecutor();
    private TextView textViewResult;
    private Button btnDetectObject, btnToggleCamera;
    private ImageView imageViewResult;
//    private CameraView cameraView;
    private Button button;
//////////////////////


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

<<<<<<< Updated upstream
=======

        }
    };

>>>>>>> Stashed changes
//    @Override
//    public void onImage(CameraKitImage cameraKitImage) {
//
//        Bitmap bitmap = cameraKitImage.getBitmap();
//
//        bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);
//
//        imageViewResult.setImageBitmap(bitmap);
//
//        final String results = classifier.recognizeImage(bitmap);
//
//        textViewResult.setText(results);
//        switch (results){
//            case "hotdog":
//                textViewResult.setText("hotdog:289.7; kcal;1,090 mg; 0.4 g");
//                cal+=289.7;
//                na+=1090;
//                su+=0.4;
//
//                break;
//            case "banana":
//                textViewResult.setText("banana:88.7; kcal;1 mg; 12 g");
//                cal+=88.7;
//                na+=1;
//                su+=12;
//                break;
//            case "pizza":
//                textViewResult.setText("pizza:226 kcal; 598 mg; 3.6 g");
//                cal+=226;
//                na+=598;
//                su+=3.6;
//            default:
//                textViewResult.setText("暫時沒有數據");
//        }



        Button next=(Button)findViewById(R.id.personalinfo);
//        next.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View v){
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, personalinfo.class);
//
//                intent.setClass(MainActivity.this,personalinfo.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("cal", String.valueOf(cal));
//                bundle.putString("na", String.valueOf(na));
//                bundle.putString("su", String.valueOf(su));
//
//                intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
//                startActivity(intent);
//            }
//        });


//    }
<<<<<<< Updated upstream


    public View.OnClickListener clickaddphoto = new View.OnClickListener(){
        public void onClick(View v){

//            selfphoto=findViewById(R.id.imageView10);
//            selfphoto.setImageResource(R.drawable.kiki);
//            addphoto.setVisibility(View.INVISIBLE);

            Intent camera_intent
                    = new Intent(MediaStore
                    .ACTION_IMAGE_CAPTURE);

            startActivityForResult(camera_intent, pic_id);

            addphoto.setVisibility(View.INVISIBLE);



        }
    };

=======
>>>>>>> Stashed changes


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

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == pic_id) {

            Bitmap photo = (Bitmap) data.getExtras()
                    .get("data");

            selfphoto.setImageBitmap(photo);
        }

    }

//    private void initTensorFlowAndLoadModel() {
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    classifier = TensorFlowImageClassifier.create(
//                            getAssets(),
//                            MODEL_FILE,
//                            LABEL_FILE,
//                            INPUT_SIZE,
//                            IMAGE_MEAN,
//                            IMAGE_STD,
//                            INPUT_NAME,
//                            OUTPUT_NAME);
////                        makeButtonVisible();
//                } catch (final Exception e) {
//                    throw new RuntimeException("Error initializing TensorFlow!", e);
//                }
//            }
//        });
//    }

}



