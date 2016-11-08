package com.yangyang.Hongniu.DecoratorParttern;

public class Women {

    private int beautyIndex;
    private int iq;
    private String name;

    public Women(int beautyIndex, int iq,String name) {
        this.beautyIndex = beautyIndex;
        this.iq = iq;
        this.name = name;
    }

    public int getBeautyIndex() {
        return beautyIndex;
    }

    public int getIq() {
        return iq;
    }

    public String getName() {
        return name;
    }

    public void say(){
        System.out.println("my name: "+name+" ,beautyIndex: "+this.getBeautyIndex()+" iq: "+this.getIq());
    }
}
