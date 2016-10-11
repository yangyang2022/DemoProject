package com.yangyang.utils;

import java.util.function.Consumer;
import java.util.function.Function;

public class MessureTimeUtils {

    private static long measureAddTime(Function<Double,Double> function, String obj){
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; ++i) {
            long start = System.nanoTime();
            //function.apply(obj);
            long duration = (System.nanoTime() - start)/1_000_000;
            if(duration < fastest) fastest = duration;
        }
        return fastest;
    }

    private static void messeaure(Consumer<String> consumer, String s){
        long start = System.currentTimeMillis();
        consumer.accept(s);
        System.out.println("Time: "+(System.currentTimeMillis() - start) +" millis.");
    }
}
