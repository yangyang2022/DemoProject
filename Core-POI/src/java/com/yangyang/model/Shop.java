package com.yangyang.model;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Shop {
    private static Random random = new Random();
    private String shopName;

    public Shop() {
    }

    public Shop(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    private static void delay(){
        try {
            int delay = 500 + random.nextInt(2000);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private double calculatePrice(String product){
        delay();
        return random.nextDouble() * product.charAt(0)+product.charAt(1);
    }
    public double getPrice(String product){
        return calculatePrice(product);
    }

    public Future<Double> getPriceAsyn(String product){
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }
    public String getPriceString(String product){
        double price = calculatePrice(product);
        Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s",shopName,price,code);
    }
}
