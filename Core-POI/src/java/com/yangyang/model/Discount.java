package com.yangyang.model;


import java.util.Random;

public class Discount {
    private static Random random = new Random();
    public enum Code{
        NONE(0),SILVER(5),GOLD(10),PLATINUM(15),DIAMOND(20)
        ;
        public int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }
    //private static void delay(){
    //    try {
    //        Thread.sleep(1000);
    //    } catch (InterruptedException e) {
    //        e.printStackTrace();
    //    }
    //}
    public static String applyDiscount(Quote quote){
        return quote.getShopName() + "price is: "+apply(quote.getPrice(),quote.getDiscountCode());
    }
    private static String formatDouble(double price){
        return String.format("%.2f",price);
    }
    private static String apply(double price,Code code){
        //delay();
        return formatDouble(price * (100 - code.percentage ) / 100);
    }
}
