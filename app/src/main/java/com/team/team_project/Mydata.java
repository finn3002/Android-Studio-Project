package com.team.team_project;

public class Mydata {
    double rec_calorie=0.0;
    double rec_carbon=0.0;
    double rec_fat=0.0;
    double rec_protein=0.0;
    int today_calorie=0;
    int today_carbon=0;
    int today_fat=0;
    int today_protein=0;
    int totalresult=0;
    int remainingresult=0;
    int moneyresult=0;
    public Mydata(int totalresult,int remainingresult,int moneyresult){
        this.totalresult=totalresult;
        this.remainingresult=remainingresult;
        this.moneyresult=moneyresult;
    }


    public int getTotalresult() {
        return totalresult;
    }

    public void setTotalresult(int totalresult) {
        this.totalresult = totalresult;
    }

    public int getRemainingresult() {
        return remainingresult;
    }

    public void setRemainingresult(int remainingresult) {
        this.remainingresult = remainingresult;
    }

    public int getMoneyresult() {
        return moneyresult;
    }

    public void setMoneyresult(int moneyresult) {
        this.moneyresult = moneyresult;
    }

    public double getRec_calorie() {
        return rec_calorie;
    }

    public void setRec_calorie(double rec_calorie) {
        this.rec_calorie = rec_calorie;
    }

    public double getRec_carbon() {
        return rec_carbon;
    }

    public void setRec_carbon(double rec_carbon) {
        this.rec_carbon = rec_carbon;
    }

    public double getRec_fat() {
        return rec_fat;
    }

    public void setRec_fat(double rec_fat) {
        this.rec_fat = rec_fat;
    }

    public double getRec_protein() {
        return rec_protein;
    }

    public void setRec_protein(double rec_protein) {
        this.rec_protein = rec_protein;
    }

    public int getToday_calorie() {
        return today_calorie;
    }

    public void setToday_calorie(int today_calorie) {
        this.today_calorie = today_calorie;
    }

    public int getToday_carbon() {
        return today_carbon;
    }

    public void setToday_carbon(int today_carbon) {
        this.today_carbon = today_carbon;
    }

    public int getToday_fat() {
        return today_fat;
    }

    public void setToday_fat(int today_fat) {
        this.today_fat = today_fat;
    }

    public int getToday_protein() {
        return today_protein;
    }

    public void setToday_protein(int today_protein) {
        this.today_protein = today_protein;
    }
}
