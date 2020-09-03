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
    Button tablebutton,foodbutton,profilebutton,chatbutton,addbutton,cakedetail,cheesedetail;
    TextView remainingresult,totalmoney,totalresult;
    PieChart pieChart;
    //ListView
    private FirebaseFirestore db ;
    private MyAdapter adapter;
    private ArrayList<foodSet> foodsets= new ArrayList<>();
    private RecyclerView recyclerView;
    private GlobalV gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DecimalFormat nf = new DecimalFormat("0");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
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
//      上方資料顯示處
        gv.setCal((gv.getCal()-gv.getAddcal()));
        remainingresult.setText((nf.format( gv.getCal())));
        totalmoney.setText((nf.format( gv.getDollar())));
        totalresult.setText((nf.format( gv.getAddcal())));

    //recyclerView Set
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL)); //設定分割線
        recyclerView.setLayoutManager(linearLayoutManager);

        getFood(recyclerView);
        //蛋糕詳細資料
        cakedetail  = findViewById(R.id.cakedetail);
        cakedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Cake.class);
                startActivity(intent);
            }
        });
        //起司蛋餅詳細資料
        cheesedetail  = findViewById(R.id.cheesedetail);
        cheesedetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddActivity.this,Cheese.class);
                startActivity(intent);
            }
        });

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
        Values.add(new PieEntry(34,""));
        Values.add(new PieEntry(66,""));
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
        final CollectionReference mydb=db.collection("personal")
                .document("personTest").collection("allfood");


        Query query = mydb;
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        foodSet bean = new foodSet();
//                        Log.e("food",document.getData().toString());
                        bean.setFoodNm(document.getData().get("foodnm").toString());
                        bean.setFoodPrice(Integer.valueOf(document.getData().get("food_price").toString()));
                        bean.setFoodCal(Double.valueOf(document.getData().get("food_calorie").toString()));

                        foodsets.add(bean);
                        adapter= new MyAdapter(foodsets);
                        adapter.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter);

                    }
                }

            }
        });
    }
}

