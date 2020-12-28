package com.team.team_project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment4 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    BarChart stackedChat;

    int[] color = new int[]{
            Color.rgb(191, 39, 80),
//            231 101 00
            Color.rgb(253, 101, 0),

//            246 206 93
            Color.rgb(243,197 ,0 )
            //87 111 134
    };
    private GlobalV gv;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment4 newInstance(String param1, String param2) {
        Fragment4 fragment = new Fragment4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_4, container, false);
        stackedChat = view.findViewById(R.id.chart_bar);
        BarDataSet barDataSet = new BarDataSet(dataValue(), "");
        barDataSet.setColors(color);
        BarData barData = new BarData(barDataSet);
        Legend legend = stackedChat.getLegend();
        legend.setTextSize(20);   //下方label字大小
        stackedChat.setData(barData);
        barData.setValueTextSize(15);
        barDataSet.setStackLabels(new String[]{"醣類","蛋白質","脂肪"});
        barDataSet.setFormSize(20);
        stackedChat.getDescription().setEnabled(false);
        XAxis xAxis = stackedChat.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(18);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        YAxis LeftYAxis = stackedChat.getAxisLeft();
        YAxis RightYAxis = stackedChat.getAxisRight();
        LeftYAxis.setTextSize(15);
        RightYAxis.setEnabled(false);   //不顯示右側
        barDataSet.setHighlightEnabled(false);
        stackedChat.animateXY(1000, 2000);
        return view;
    }

    private ArrayList<BarEntry> dataValue() {
        String[] xAxisLables = new String[]{"9/17","9/18","9/19", "9/20", "9/21", "9/22", "9/23", "9/24"};
        stackedChat.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xAxisLables));
        ArrayList<BarEntry> dataValue = new ArrayList<>();
        dataValue.add(new BarEntry(1, new float[]{300, 35, 45}));
        dataValue.add(new BarEntry(2, new float[]{200, 40, 35}));
        dataValue.add(new BarEntry(3, new float[]{120, 22, 40}));
        dataValue.add(new BarEntry(4, new float[]{100, 30, 45}));
        dataValue.add(new BarEntry(5, new float[]{250, 50, 50}));
        dataValue.add(new BarEntry(6, new float[]{320, 62, 38}));
        dataValue.add(new BarEntry(7, new float[]{300, 35, 40}));
        return dataValue;
    }
    


}

