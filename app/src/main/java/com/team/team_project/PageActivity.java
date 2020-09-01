package com.team.team_project;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup;

import java.text.DecimalFormat;

public class PageActivity extends Activity {

    Button addbutton;
    Button tablebutton;
    Button foodbutton;
    Button chatbutton;
    TextView fieldheight, fieldweight, fieldage;
    RadioGroup sex, work;
    RadioButton no, low, active, high;
    TextView result,carresult,porresult,fatresult;


    private GlobalV gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page);
        gv= (GlobalV) getApplication();


        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(calcBMI);
        sex = findViewById(R.id.sex);
        work = findViewById(R.id.work);
        no = findViewById(R.id.no);
        low = findViewById(R.id.low);
        active = findViewById(R.id.active);
        high = findViewById(R.id.highactive);
        fieldheight = (TextView) findViewById(R.id.edheight);
        fieldweight = (TextView) findViewById(R.id.edweight);
        fieldage = (TextView) findViewById(R.id.editage);
        result = (TextView) findViewById(R.id.result);
        carresult =(TextView)  findViewById(R.id.carresult);
        porresult = (TextView) findViewById(R.id.portineresult);
        fatresult = (TextView) findViewById(R.id.fatresult);
        DecimalFormat nf = new DecimalFormat("0");

        addbutton = findViewById(R.id.pencil);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });
        tablebutton = findViewById(R.id.table);
        tablebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(PageActivity.this, Analysis.class);
                startActivity(intent);
            }
        });

        foodbutton = findViewById(R.id.recommend);
        foodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageActivity.this, Food.class);
                startActivity(intent);
            }
        });
        chatbutton = findViewById(R.id.chat);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PageActivity.this,Chat.class);
                startActivity(intent);
            }
        });

        fieldheight.setText((nf.format( gv.getHeight())));
        fieldweight.setText((nf.format( gv.getWeight())));
        fieldweight.setText((nf.format( gv.getWeight())));
        fieldage.setText((nf.format( gv.getAge())));
    }

    public View.OnClickListener calcBMI = new View.OnClickListener() {

        public void onClick(View v) {


            DecimalFormat nf = new DecimalFormat("0");
            //身高
//            double height = Double.parseDouble(fieldheight.getText().toString());
            //體重
//            double weight = Double.parseDouble(fieldweight.getText().toString());
            //年齡
//            int age = Integer.parseInt(fieldage.getText().toString());

            double BMRboy = 66 + (13.7 * gv.getWeight()) + (5.0 *  gv.getHeight()) - (6.8 * gv.getAge());
            double BMRgirl = 655 + (9.6 * gv.getWeight()) + (1.8 *  gv.getHeight()) - (4.7 * gv.getAge());

            if (sex.getCheckedRadioButtonId() == R.id.boy) {

                switch (work.getCheckedRadioButtonId()) {

                    case R.id.no:
                        gv.setCal((BMRboy * 1.2));
                        gv.setCarbonhydrate((gv.getCal() * 0.65) / 4);
                        gv.setProtein((gv.getCal() * 0.125 / 4));
                        gv.setFat((gv.getCal() * 0.225 / 9));

                        //開算
                        result.setText((nf.format( gv.getCal())));
                        carresult.setText(nf.format(gv.getCarbohydrate()));
                        porresult.setText(nf.format(gv.getProtein()));
                        fatresult.setText(nf.format(gv.getFat()));

                    case R.id.low:
                        gv.setCal(BMRboy * 1.375);
                        gv.setCarbonhydrate((gv.getCal() * 0.65) / 4);
                        gv.setProtein((gv.getCal() * 0.125 / 4));
                        gv.setFat((gv.getCal() * 0.225 / 9));
                        result.setText(nf.format(gv.getCal()));
                        carresult.setText(nf.format(gv.getCarbohydrate()));
                        porresult.setText(nf.format(gv.getProtein()));
                        fatresult.setText(nf.format(gv.getFat()));
                        break;
                    case R.id.active:
                        gv.setCal(BMRboy * 1.55);
                        gv.setCarbonhydrate((gv.getCal() * 0.65) / 4);
                        gv.setProtein((gv.getCal() * 0.125 / 4));
                        gv.setFat((gv.getCal() * 0.225 / 9));

                        result.setText(nf.format(gv.getCal()));
                        carresult.setText(nf.format(gv.getCarbohydrate()));
                        porresult.setText(nf.format(gv.getProtein()));
                        fatresult.setText(nf.format(gv.getFat()));
                        break;
                    case R.id.highactive:
                        gv.setCal(BMRboy * 1.725);
                        gv.setCarbonhydrate((gv.getCal() * 0.65) / 4);
                        gv.setProtein((gv.getCal() * 0.125 / 4));
                        gv.setFat((gv.getCal() * 0.225 / 9));
                        result.setText(nf.format(gv.getCal()));
                        carresult.setText(nf.format(gv.getCarbohydrate()));
                        porresult.setText(nf.format(gv.getProtein()));
                        fatresult.setText(nf.format(gv.getFat()))  ;
                        break;

                }
            }else if (sex.getCheckedRadioButtonId() == R.id.girl) {

                switch (work.getCheckedRadioButtonId()) {
                    case R.id.no:
                        gv.setCal( BMRgirl * 1.2);
                        gv.setCarbonhydrate((gv.getCal()*0.65)/4);
                        gv.setProtein((gv.getCal()*0.125/4));
                        gv.setFat((gv.getCal()*0.225/9));

                        result.setText(nf.format(gv.getCal()));
                        carresult.setText(nf.format(gv.getCarbohydrate()));
                        porresult.setText(nf.format(gv.getProtein()));
                        fatresult.setText(nf.format(gv.getFat()));
                        break;
                    case R.id.low:
                        gv.setCal( BMRgirl * 1.375);
                        gv.setCarbonhydrate((gv.getCal()*0.65)/4);
                        gv.setProtein((gv.getCal()*0.125/4));
                        gv.setFat((gv.getCal()*0.225/9));

                        result.setText(nf.format(gv.getCal()));
                        carresult.setText(nf.format(gv.getCarbohydrate()));
                        porresult .setText(nf.format(gv.getProtein()));
                        fatresult.setText(nf.format(gv.getFat()));
                        break;
                    case R.id.active:
                        gv.setCal( BMRgirl * 1.55);
                        gv.setCarbonhydrate((gv.getCal()*0.65)/4);
                        gv.setProtein((gv.getCal()*0.125/4));
                        gv.setFat((gv.getCal()*0.225/9));
                        result.setText(nf.format(gv.getCal()));
                        carresult.setText(nf.format(gv.getCarbohydrate()));
                        porresult .setText(nf.format(gv.getProtein()));
                        fatresult.setText(nf.format(gv.getFat()));
                        break;
                    case R.id.highactive:
                        gv.setCal( BMRgirl * 1.725);
                        gv.setCarbonhydrate((gv.getCal()*0.65)/4);
                        gv.setProtein((gv.getCal()*0.125/4));
                        gv.setFat((gv.getCal()*0.225/9));
                        result.setText(nf.format(gv.getCal()));
                        carresult.setText(nf.format(gv.getCarbohydrate()));
                        porresult .setText(nf.format(gv.getProtein()));
                        fatresult.setText(nf.format(gv.getFat()));
                        break;
                }
            }
            };
        };
    };










