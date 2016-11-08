package com.yangyang.reflactor.chapter1;

public class ChildrenPrice extends Price{
    @Override
    int getPriceCode() {
        return Movie.CHILDRENS;
    }

    public double getCharge(int daysRented){
        double result = 1.5;
        if(daysRented > 3)
            result += (daysRented-3)*1.5;
        return result;
    }
}
