package com.yangyang.web.model;

import java.util.List;

public class ShopCart {
    private List<Goods> goodses;

    public ShopCart() {
    }

    public ShopCart(List<Goods> goodses) {
        this.goodses = goodses;
    }
}
