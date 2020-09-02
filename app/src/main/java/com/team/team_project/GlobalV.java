package com.team.team_project;

import android.app.Application;
import android.view.View;

import java.util.DuplicateFormatFlagsException;

public class GlobalV extends Application {
    private double cal,addcal;
    private double carbonhydrate;
    private double protein;
    private double fat,height,weight;
    private int dollar,age;

    @Override
    public void onCreate() {
        super.onCreate();
        setCal(CAL);
        setAddcal(ADDCAL);
        setDollar(Dollar);
        setAge(AGE);
        setHeight(HEIGHT);
        setWeight(WEIGHT);

    }

    public Double getCal() {

        return cal;
    }
    public Double getAddcal() {

        return addcal;
    }
    public Double getCarbohydrate(){
        return carbonhydrate;
    }
    public Double getProtein(){
        return protein;
    }
    public Double getFat(){
        return fat;
    };
    public Double getHeight(){
        return height;
    };
    public Double getWeight(){
        return weight;
    };
    public int getDollar(){
        return dollar;
    }
    public int getAge(){
        return age;
    }
    public void setCal(Double cal) {
        this.cal = cal;
    }
    public void setAddcal(Double addcal) {
        this.addcal = addcal;
    }
    public void setCarbonhydrate(Double carbonhydrate) {
        this.carbonhydrate = carbonhydrate;
    }
    public void setProtein(Double protein) {
        this.protein = protein;
    }
    public void setFat(Double fat) {
        this.fat = fat;
    }
    public void setHeight(Double height) {
        this.height = height;
    }
    public void setWeight(Double weight) {
        this.weight = weight;
    }
    public void setDollar(int dollar) {
        this.dollar = dollar;
    }
    public void setAge(int age) {
        this.age = age;
    }

    //    初始值
    private static final double CAL = 0.0;
    private static final double ADDCAL=0.0;
    private static final int Dollar =0;
    private static final int AGE =21;
    private static final double HEIGHT =170.0;
    private static final double WEIGHT =56.5;

}

