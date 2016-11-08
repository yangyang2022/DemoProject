package com.yangyang.Util;

public class SleepUtil {

    public static void SleepSecond(int second){
        SleepMills(second*1000);
    }
    public static void SleepMills(int mill){

        try {
            Thread.sleep(mill);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void sleep1s(){
        SleepSecond(1);
    }
    public static void sleep2s(){
        SleepSecond(2);
    }
    public static void sleep3s(){
        SleepSecond(3);
    }
    public static void sleep4s(){
        SleepSecond(4);
    }
    public static void sleep5s(){
        SleepSecond(5);
    }
    public static void sleep10s(){
        SleepSecond(10);
    }

}
