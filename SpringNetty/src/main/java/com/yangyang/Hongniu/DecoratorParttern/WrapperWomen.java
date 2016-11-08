package com.yangyang.Hongniu.DecoratorParttern;

public abstract class WrapperWomen extends Women{

    private Women women;

    public WrapperWomen(Women women) {
        super(women.getBeautyIndex(),women.getIq(),women.getName());
        this.women = women;
    }

    @Override
    public int getBeautyIndex() {
        return women.getBeautyIndex();
    }

    @Override
    public int getIq() {
        return women.getIq();
    }

    @Override
    public void say() {
        women.say();
    }

    @Override
    public String getName() {
        return women.getName();
    }
}
