package com.team.team_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FoodCamActivity extends AppCompatActivity {
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

    private Classifier classifier;
    private Executor executor = Executors.newSingleThreadExecutor();
    private TextView textViewResult;
    private Button btnDetectObject, btnToggleCamera;
    private ImageView imageViewResult;
    private CameraView cameraView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_cam);
        cameraView = (CameraView) findViewById(R.id.cameraView);
        btnDetectObject = (Button) findViewById(R.id.btnDetectObject);
        cameraView = (CameraView) findViewById(R.id.cameraView);
//        imageViewResult = (ImageView) findViewById(R.id.imageViewResult);
//        textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewResult.setMovementMethod(new ScrollingMovementMethod());

//        btnToggleCamera = (Button) findViewById(R.id.btnToggleCamera);


        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {

                Bitmap bitmap = cameraKitImage.getBitmap();

                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);

                imageViewResult.setImageBitmap(bitmap);

                final String results = classifier.recognizeImage(bitmap);

                textViewResult.setText(results);
                switch (results){
                    case "hotdog":
                        textViewResult.setText("hotdog:289.7; kcal;1,090 mg; 0.4 g");
                        cal+=289.7;
                        na+=1090;
                        su+=0.4;

                        break;
                    case "banana":
                        textViewResult.setText("banana:88.7; kcal;1 mg; 12 g");
                        cal+=88.7;
                        na+=1;
                        su+=12;
                        break;
                    case "pizza":
                        textViewResult.setText("pizza:226 kcal; 598 mg; 3.6 g");
                        cal+=226;
                        na+=598;
                        su+=3.6;
                    default:
                        textViewResult.setText("暫時沒有數據");
                }



//                Button next=(Button)findViewById(R.id.personalinfo);
//                next.setOnClickListener(new View.OnClickListener(){
//                    public void onClick(View v){
//                        Intent intent = new Intent();
//                        intent.setClass(MainActivity.this, personalinfo.class);
//
//                        intent.setClass(MainActivity.this,personalinfo.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("cal", String.valueOf(cal));
//                        bundle.putString("na", String.valueOf(na));
//                        bundle.putString("su", String.valueOf(su));
//
//                        intent.putExtras(bundle);   // 記得put進去，不然資料不會帶過去哦
//                        startActivity(intent);
//                    }
//                });


            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });

        btnToggleCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.toggleFacing();
            }
        });

        btnDetectObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraView.captureImage();
            }
        });

        initTensorFlowAndLoadModel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                classifier.close();
            }
        });
    }

    private void initTensorFlowAndLoadModel() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    classifier = TensorFlowImageClassifier.create(
                            getAssets(),
                            MODEL_FILE,
                            LABEL_FILE,
                            INPUT_SIZE,
                            IMAGE_MEAN,
                            IMAGE_STD,
                            INPUT_NAME,
                            OUTPUT_NAME);
                    makeButtonVisible();
                } catch (final Exception e) {
                    throw new RuntimeException("Error initializing TensorFlow!", e);
                }
            }
        });
    }

    private void makeButtonVisible() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnDetectObject.setVisibility(View.VISIBLE);
            }
        });
    }

}
