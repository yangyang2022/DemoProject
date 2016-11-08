package com.yangyang.reflactor.chapter1;

abstract public class Price {

    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    public int getFrequentRenterPoint(int daysRented){
        return ((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1) ? 2 : 1;
    }

}
