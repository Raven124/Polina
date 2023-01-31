package com.example.testr;

public class Delo {
    private String NameDelo;
    private String StartDelo;
    private String FinishDelo;
    private Float RatingDelo;

    public Delo(String NameDelo, String Startdelo, String FinishDelo, Float RatingDelo) {
        this.NameDelo = NameDelo;
        this.StartDelo = Startdelo;
        this.FinishDelo = FinishDelo;
        this.RatingDelo = RatingDelo;
    }

    public String getNameDelo() {
        return this.NameDelo;
    }

    public void setNameDelo(String nameDelo) {
        this.NameDelo = nameDelo;
    }

    public String getStartDelo() {
        return this.StartDelo;
    }

    public void setStartDelo(String startDelo) {
        this.StartDelo = startDelo;
    }

    public String getFinishDelo() {
        return FinishDelo;
    }

    public void setFinishDelo(String finishDelo) {
        FinishDelo = finishDelo;
    }

    public Float getRatingDelo() {
        return RatingDelo;
    }

    public void setRatingDelo(Float ratingDelo) {
        RatingDelo = ratingDelo;
    }
}

