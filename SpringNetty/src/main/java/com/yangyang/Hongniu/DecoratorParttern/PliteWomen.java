package com.yangyang.Hongniu.DecoratorParttern;

public class PliteWomen extends WrapperWomen{

    public PliteWomen(Women women) {
        super(women);
    }

    @Override
    public void say() {
        System.out.println("hello erverybody!");
        super.say();
        System.out.println("Goodbye erverybody!");
    }
}
