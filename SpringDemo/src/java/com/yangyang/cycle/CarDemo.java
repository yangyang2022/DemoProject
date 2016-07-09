package com.yangyang.cycle;

public class CarDemo {
    private String brand;
    
    public CarDemo(){
        System.out.println("car demo construtor ... ");
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void init(){
        System.out.println("carDemo init ... ");
    }
    public void destroy(){
        System.out.println("carDemo destroy ... ");
    }
}
