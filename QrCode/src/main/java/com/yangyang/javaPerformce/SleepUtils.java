package com.yangyang.javaPerformce;

public class SleepUtils {

    public static void sleepSeconds(long seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
