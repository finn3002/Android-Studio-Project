package com.team.team_project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    PieChart pieChart;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment3() {
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
    public static Fragment3 newInstance(String param1, String param2) {
        Fragment3 fragment = new Fragment3();
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
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        pieChart =(PieChart)view.findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.7f);
        pieChart.setCenterText("今日\n營養攝取");
        pieChart.setCenterTextSize(25);
        pieChart.setCenterTextColor(Color.WHITE);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(android.R.color.white);
        pieChart.setTransparentCircleAlpha(80);
        pieChart.setTransparentCircleRadius(58f);
        pieChart.getLegend().setEnabled(false);

        ArrayList<PieEntry> Values = new ArrayList<>();
        Values.add(new PieEntry(34,"碳水化合物"));
        Values.add(new PieEntry(23,"蛋白質"));
        Values.add(new PieEntry(14,"脂肪"));
        Values.add(new PieEntry(35,"鈉"));
        Values.add(new PieEntry(23,"糖"));

        pieChart.animateY(2000, Easing.EaseInOutCubic);
        PieDataSet dataSet = new PieDataSet(Values,"");
        dataSet.setSliceSpace(3f);
        dataSet.getSelectionShift();
        dataSet.setValueTextSize(20f);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data  = new PieData((dataSet));

        data.setValueTextSize(20f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);
        // Inflate the layout for this fragment
        return view;
    }
}
