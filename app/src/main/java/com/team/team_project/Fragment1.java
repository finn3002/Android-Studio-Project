package com.team.team_project;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    PieChart pieChart;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button proteinBt;
    private Button fatBt;
    private Button sugarBt;
    private TextView firstitem;
    private TextView seconditem;
    private TextView thirditem;
    private TextView explain2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        proteinBt=getActivity().findViewById(R.id.proteinBt);
        fatBt=getActivity().findViewById(R.id.fatBt);
        sugarBt=getActivity().findViewById(R.id.sugarBt);
        firstitem=getActivity().findViewById(R.id.firstitem);
        seconditem=getActivity().findViewById(R.id.seconditem);
        thirditem=getActivity().findViewById(R.id.thirditem);
        explain2=getActivity().findViewById(R.id.explain2);
        proteinBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstitem.setText("茶葉蛋");
                seconditem.setText("鹽水雞");
                thirditem.setText("木瓜牛奶");
                explain2.setText("每單位價格可獲得的蛋白質");
            }
        });
        sugarBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstitem.setText("烤地瓜");
                seconditem.setText("飯糰");
                thirditem.setText("焗烤馬鈴薯");
                explain2.setText("每單位價格可獲得的醣類");
            }
        });
        fatBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstitem.setText("甜甜圈");
                seconditem.setText("酥皮濃湯");
                thirditem.setText("鹽酥雞");
                explain2.setText("每單位價格可獲得的脂肪");
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false);
    }
}
