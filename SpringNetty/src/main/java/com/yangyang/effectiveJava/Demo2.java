package com.yangyang.effectiveJava;

public class Demo2 {


    private int lastUsage(){return 99;}
    private double baseCharge(){
        double result = Math.min(lastUsage(),100)*0.03;
        if(lastUsage()> 100){
            result+=(Math.min(lastUsage(),200)-100)*0.05;
        }
        if(lastUsage() > 200){
            result += (lastUsage() - 200)*0.07;
        }
        return result;
    }
    public static void main(String[] args) {



    }
}
