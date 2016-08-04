package com.yangyang.javaTest;

import org.junit.Test;

import java.util.stream.IntStream;

public class Demo1 {
    @Test
    public void testDemo1() {
        int[] list = new int[]{111,12,223,854,-5656,53};
        IntStream.of(list)
                .sorted()
                .forEach(System.out::println);
    }
}
