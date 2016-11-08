package com.yangyang.Hongniu.DecoratorParttern;

public class DressupWomen extends WrapperWomen{

    public DressupWomen(Women women) {
        super(women);
    }

    @Override
    public int getBeautyIndex() {
        return super.getBeautyIndex()+20;
    }
}
