package com.team.team_project;

public class personInfo {

    double rec_calorie;
    double rec_carbon;
    double rec_fat;
    double rec_protein;
    int today_calorie;
    int today_carbon;
    int today_fat;
    int today_protein;

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

    public double getToday_calorie() {
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

    @Override
    public String toString() {
        return "personInfo{" +
                "rec_calorie=" + rec_calorie +
                ", rec_carbon=" + rec_carbon +
                ", rec_fat=" + rec_fat +
                ", rec_protein=" + rec_protein +
                ", today_calorie=" + today_calorie +
                ", today_carbon=" + today_carbon +
                ", today_fat=" + today_fat +
                ", today_protein=" + today_protein +
                '}';
    }
}
