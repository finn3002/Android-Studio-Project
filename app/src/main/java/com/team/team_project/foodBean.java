package com.team.team_project;

import java.sql.Timestamp;
import java.util.ArrayList;

public class foodBean {

    private String foodnm;
    private Double food_protein;
    private int food_price;
    private Double food_fat;
    private Double food_calorie;
    private Double food_carbon;
    private String keyin;
    foodBean(){
        this.foodnm="";
        this.food_protein=0.0;
        this.food_price=0;
        this.food_fat=0.0;
        this.food_calorie=0.0;
        this.food_carbon=0.0;
        this.keyin="";
    }

    public String getFoodnm() {
        return foodnm;
    }

    public void setFoodnm(String foodnm) {
        this.foodnm = foodnm;
    }

    public Double getFood_protein() {
        return food_protein;
    }

    public void setFood_protein(Double food_protein) {
        this.food_protein = food_protein;
    }

    public int getFood_price() {
        return food_price;
    }

    public void setFood_price(int food_price) {
        this.food_price = food_price;
    }

    public Double getFood_fat() {
        return food_fat;
    }

    public void setFood_fat(Double food_fat) {
        this.food_fat = food_fat;
    }

    public Double getFood_calorie() {
        return food_calorie;
    }

    public void setFood_calorie(Double food_calorie) {
        this.food_calorie = food_calorie;
    }

    public Double getFood_carbon() {
        return food_carbon;
    }

    public void setFood_carbon(Double food_carbon) {
        this.food_carbon = food_carbon;
    }

    public String getKeyin() {
        return keyin;
    }

    public void setKeyin(String keyin) {
        this.keyin = keyin;
    }

    @Override
    public String toString() {
        return "foodBean{" +
                "foodnm='" + foodnm + '\'' +
                ", food_protein=" + food_protein +
                ", food_price=" + food_price +
                ", food_fat=" + food_fat +
                ", food_calorie=" + food_calorie +
                ", food_carbon=" + food_carbon +
                ", keyin=" + keyin +
                '}';
    }
}