package com.yangyang.Hongniu.simpleFactory;

public class MainTest {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {

        //FordCar car = new FordCar();
        //HondaCar hondaCar = new HondaCar();

        //Family family = new Family(CarFactory.getInstance(HondaCar.class));
        //
        //family.goOut();

        Family family = new Family(CarFactory.getCar());
        family.goOut();



    }
}
