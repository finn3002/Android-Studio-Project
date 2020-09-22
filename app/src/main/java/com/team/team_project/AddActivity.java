package com.team.team_project;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.text.DecimalFormat;
import java.util.ArrayList;
public class AddActivity extends Activity {
    //顏色黃→白
    final int[] MY_COLORS = {
            Color.  rgb(255,204,0),
            Color. rgb(255,255,255)
    };
    Button tablebutton,foodbutton,profilebutton,chatbutton,addbutton;
    TextView remainingresult,totalmoney,totalresult;
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
        remainingresult.setText((nf.format( gv.getCal())));
        totalmoney.setText((nf.format( gv.getDollar())));
        totalresult.setText((nf.format( gv.getAddcal())));


//        上方pieChart
        pieChart =(PieChart)findViewById(R.id.pieChart);
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
        Values.add(new PieEntry(Float.parseFloat(gv.getCal().toString())-Float.parseFloat(gv.getAddcal().toString()),""));
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

    public void  getFood(View v){ //食物查詢
        final DecimalFormat nf = new DecimalFormat("0");
        final CollectionReference mydb=db.collection("personal")
                .document("personTest").collection("allfood");


        Query query = mydb;
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
                        CountaddCal+=bean.getFood_calorie();
                        CountPrice+=bean.getFood_price();
                    }
                    gv.setAddcal((double)CountaddCal);
                    gv.setDollar(CountPrice);
                    gv.setCal((gv.getCal()-gv.getAddcal()));

                    totalmoney.setText(String.valueOf(CountPrice));
                    totalresult.setText(String.valueOf(CountaddCal));
                    remainingresult.setText(String.valueOf(nf.format(gv.getCal())));

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
                Intent intent = new Intent(AddActivity.this,AddDetail.class);
                startActivity(intent);
            }
        });
        remainingresult = findViewById(R.id.remainingresult);
        totalmoney=findViewById(R.id.moneyresult);
        totalresult=findViewById(R.id.totalresult);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
    }

    private void settingRecycleV(){
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //設定分割線
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}

