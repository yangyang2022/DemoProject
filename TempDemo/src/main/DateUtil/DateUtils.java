package com.yangyang.java8;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Demo1 {
    @Test
    public void testDemo() {
        LocalDateTime time1 = LocalDateTime.of(1900, 1, 1,0,0,0);
        LocalDateTime time2 = LocalDateTime.of(2016, 7, 23,0,0,0);
        Duration d = Duration.between(time1,time2);
        int dayTime = 3600*24;
        System.out.println(d.get(ChronoUnit.SECONDS) / dayTime);
    }
}
