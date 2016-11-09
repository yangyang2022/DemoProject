package com.yangyang.reflactor.chapter1;

public class NewReleasePrice extends Price{
    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
    public double getCharge(int daysRented){
        return daysRented*3;
    }
}