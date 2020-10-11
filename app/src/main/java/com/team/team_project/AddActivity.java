package com.team.team_project;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends Activity {
    //顏色黃→白
    final int[] MY_COLORS = {
            Color.  rgb(255,204,0),
            Color. rgb(255,255,255)
    };
    Button tablebutton,foodbutton,profilebutton,chatbutton,addbutton,upBt,downBt;
    TextView remainingresult,totalmoney,totalresult;
    TextView dateNum;
    PieChart pieChart;
    //ListView
    private FirebaseFirestore db ;
    private MyAdapter adapter;
    private ArrayList<foodBean> foodbeans= new ArrayList<>();
    private RecyclerView recyclerView;
    private GlobalV gv;
    private MyAdapter.RecyclerViewClickListener listener;
    private int CountaddCal=0;
    private int CountPrice=0;
    private int Countcarbon=0;
    private int Countprotein=0;
    private int Countfat=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DecimalFormat nf = new DecimalFormat("0");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        findview();
        //recyclerView Set
        settingRecycleV();
        getFood(recyclerView);


//      上方資料顯示處
//        gv.setCal((gv.getCal()-gv.getAddcal()));
//        remainingresult.setText((nf.format( gv.getCal())));
//        totalmoney.setText((nf.format( gv.getDollar())));
//        totalresult.setText((nf.format( gv.getAddcal())));


//        上方pieChart


    }

    //重整用
    @Override
    public void onRestart() {
        super.onRestart();

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void reload(){

        Intent intent = new Intent(AddActivity.this,AddActivity.class);
        finish();
        startActivity(intent);


    }

    public void  getFood(View v){ //食物查詢
        final DecimalFormat nf = new DecimalFormat("0");
        final CollectionReference mydb=db.collection("personal")
                .document("personTest").collection("allfood");
        final DocumentReference updAllcal=db.collection("personal").document("personTest");

        Query query = mydb.whereEqualTo("keyin",gv.getDate());
        final String[] rec_calorie = {""};
        db.collection("personal").document("personTest").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    rec_calorie[0] =task.getResult().getDouble("rec_calorie").toString();

                }
            }
        });

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        foodBean bean = new foodBean();
//                        Log.e("food",document.getData().toString());
                        bean.setFoodnm(document.getData().get("foodnm").toString());
                        bean.setFood_price(Integer.valueOf(document.getData().get("food_price").toString()));
                        bean.setFood_calorie(Double.valueOf(document.getData().get("food_calorie").toString()));
                        bean.setFood_carbon(Double.valueOf(document.getData().get("food_carbon").toString()));
                        bean.setFood_protein(Double.valueOf(document.getData().get("food_protein").toString()));
                        bean.setFood_fat(Double.valueOf(document.getData().get("food_fat").toString()));
                        setOnClickListener();
                        foodbeans.add(bean);
                        adapter= new MyAdapter(foodbeans,listener);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);
                        CountaddCal+=bean.getFood_calorie();//計算總攝取
                        CountPrice+=bean.getFood_price();
                        Countfat+=bean.getFood_fat();
                        Countprotein+=bean.getFood_protein();
                        Countcarbon+=bean.getFood_carbon();
                    }
                    gv.setAddcal((double)CountaddCal);
                    gv.setDollar(CountPrice);


                    totalmoney.setText(String.valueOf(CountPrice));
                    totalresult.setText(String.valueOf(CountaddCal));
                    updAllcal.update("today_calorie",CountaddCal);
                    updAllcal.update("today_protein",Countprotein);
                    updAllcal.update("today_fat",Countfat);
                    updAllcal.update("today_carbon",Countcarbon);

//                    if (!rec_calorie[0].isEmpty()) {
//                        gv.setCal((Double.valueOf(rec_calorie[0]) - CountaddCal));
                    gv.setCal(1978.0- CountaddCal);
                        remainingresult.setText(String.valueOf(nf.format(gv.getCal())));
//                    }




                    pieChart.setUsePercentValues(true);
                    pieChart.getDescription().setEnabled(false);
                    pieChart.setExtraOffsets(5,10,5,5);
                    pieChart.setDragDecelerationFrictionCoef(0.7f);
                    pieChart.setCenterText(nf.format(gv.getAddcal()).toString());
                    pieChart.setCenterTextColor(Color.WHITE);
                    pieChart.setCenterTextSize(15);
                    pieChart.setDrawHoleEnabled(true);
                    pieChart.setHoleColor(android.R.color.white);
                    pieChart.setTransparentCircleAlpha(110);
                    pieChart.setTransparentCircleRadius(58f);
                    pieChart.setRotationEnabled(false);
                    pieChart.getLegend().setEnabled(false);
//        內容
                    ArrayList<PieEntry> Values = new ArrayList<>();
                    Values.add(new PieEntry(Float.parseFloat(gv.getAddcal().toString()),""));
                    Values.add(new PieEntry(Float.parseFloat(gv.getCal().toString()),""));


//        顏色順序
                    ArrayList<Integer> colors = new ArrayList<>();
                    for(int c: MY_COLORS) colors.add(c);
//        pie動畫
                    pieChart.animateY(2000, Easing.EaseInOutCubic);
//        顯示
                    PieDataSet dataSet = new PieDataSet(Values,"");

                    dataSet.setSliceSpace(0f);
                    dataSet.setColors(colors);
                    PieData data  = new PieData((dataSet));
                    data.setValueTextSize(0f);
                    pieChart.setData(data);

                }
            }
//            點擊觸發看食物詳細資訊
            private void setOnClickListener() {
                listener = new MyAdapter.RecyclerViewClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                    Intent intent = new Intent(getApplicationContext(), FoodProfile.class);
                        intent.putExtra("name",foodbeans.get(position).getFoodnm());
                        intent.putExtra("price",String.valueOf(foodbeans.get(position).getFood_price()));
                        intent.putExtra("cal",String.valueOf(foodbeans.get(position).getFood_calorie()));
                        intent.putExtra("carbon",String.valueOf(foodbeans.get(position).getFood_carbon()));
                        intent.putExtra("protein",String.valueOf(foodbeans.get(position).getFood_protein()));
                        intent.putExtra("fat",String.valueOf(foodbeans.get(position).getFood_fat()));
                            startActivity(intent);
                    }
                };
            }
        });
    }

    private void findview(){
        gv= (GlobalV) getApplication();
        db=FirebaseFirestore.getInstance();
        dateNum=findViewById(R.id.dateNum);
        dateNum.setText(gv.getDate());
        upBt=findViewById(R.id.upBt);
        upBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddActivity.this,AddActivity.class);
                Calendar calendar= Calendar.getInstance();
                calendar.setTime(new Date());

                DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");

                try {
                    Date mdate=fmt.parse(gv.getDate());
                    calendar.setTime(mdate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                calendar.add(Calendar.DATE,-1);
                gv.setDate(fmt.format(calendar.getTime()));

                Log.e("datatest",String.valueOf(fmt.format(calendar.getTime())));
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
        });
        downBt=findViewById(R.id.down);
        downBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,AddActivity.class);
                Calendar calendar= Calendar.getInstance();
                calendar.setTime(new Date());

                DateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");

                try {
                    Date mdate=fmt.parse(gv.getDate());
                    calendar.setTime(mdate);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                calendar.add(Calendar.DATE,1);
                gv.setDate(fmt.format(calendar.getTime()));

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            }
        });
        tablebutton  = findViewById(R.id.table);
        tablebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Analysis.class);
                startActivity(intent);
            }
        });
        foodbutton  = findViewById(R.id.recommend);
        foodbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Food.class);
                startActivity(intent);
            }
        });
        profilebutton  = findViewById(R.id.profile);
        profilebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,PageActivity.class);
                startActivity(intent);
            }
        });
        chatbutton = findViewById(R.id.chat);
        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Chat.class);
                startActivity(intent);
            }
        });
        addbutton = findViewById(R.id.additem);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dateData = new Bundle();
                dateData.putString("dateData",dateNum.getText().toString());
                Intent intent = new Intent(AddActivity.this,AddDetail.class);
                intent.putExtras(dateData);
                startActivity(intent);
            }
        });
        remainingresult = findViewById(R.id.remainingresult);
        totalmoney=findViewById(R.id.moneyresult);
        totalresult=findViewById(R.id.totalresult);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);

        pieChart =(PieChart)findViewById(R.id.pieChart);
    }

    private void settingRecycleV(){
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //設定分割線
        recyclerView.setLayoutManager(linearLayoutManager);


    }
}

