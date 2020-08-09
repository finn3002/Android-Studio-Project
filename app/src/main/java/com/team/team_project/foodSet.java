package com.team.team_project;

public class foodSet {

    String foodNm;
    int foodPrice;
    Double foodCal;
    foodSet(){
        foodNm="";
        foodPrice=0;
        foodCal=0.0;
    }
    foodSet( String foodNm,  int foodPrice,Double foodCal){
        this.foodNm=foodNm;
        this.foodPrice=foodPrice;
        this.foodCal=foodCal;
    }

    public String getFoodNm() {
        return foodNm;
    }

    public void setFoodNm(String foodNm) {
        this.foodNm = foodNm;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Double getFoodCal() {
        return foodCal;
    }

    public void setFoodCal(Double foodCal) {
        this.foodCal = foodCal;
    }

    @Override
    public String toString() {
        return "foodSet{" +
                "foodNm='" + foodNm + '\'' +
                ", foodPrice=" + foodPrice +
                ", foodCal=" + foodCal +
                '}';
    }
}
