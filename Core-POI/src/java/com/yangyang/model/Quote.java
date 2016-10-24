package com.yangyang.model;

public class Quote {
    private String shopName;
    private double price;
    private Discount.Code discountCode;

    public Quote(String shopName, double price, Discount.Code discountCode) {
        this.shopName = shopName;
        this.price = price;
        this.discountCode = discountCode;
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDiscountCode() {
        return discountCode;
    }
    public static Quote parse(String s){
        String[] splite = s.split(":");
        String shopName = splite[0];
        double price = Double.parseDouble(splite[1]);
        Discount.Code discountCodde = Discount.Code.valueOf(splite[2]);
        return new Quote(shopName,price,discountCodde);
    }
}
