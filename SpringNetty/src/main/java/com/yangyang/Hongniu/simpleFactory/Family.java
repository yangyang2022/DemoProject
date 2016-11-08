package com.yangyang.Hongniu.simpleFactory;

public class Family {
    private Car car;

    public Family(Car car) {
        this.car = car;
    }

    public void goOut(){
        System.out.println("family go out!");
        car.run();
        System.out.println("have fun!");
    }
}
