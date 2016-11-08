package com.yangyang.Hongniu.DecoratorParttern;

public class EducatedWomen extends WrapperWomen{

    public EducatedWomen(Women women) {
        super(women);
    }

    @Override
    public int getIq() {
        return super.getIq()+20;
    }
}
