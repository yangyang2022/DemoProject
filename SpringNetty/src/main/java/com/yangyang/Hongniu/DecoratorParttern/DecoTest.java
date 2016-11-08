package com.yangyang.Hongniu.DecoratorParttern;

public class DecoTest {
    public static void main(String[] args) {

        Women women = new Women(80, 100, "Baby");

        Women baby = new PliteWomen(new EducatedWomen(new DressupWomen(women)));

        System.out.println(baby.getName()+" : "+baby.getBeautyIndex() + " : " + baby.getIq());
        baby.say();

    }
}
