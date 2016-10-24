package com.yangyang.javaPerformce;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    public static void main(String[] args) throws IOException {

        //long sum = Files.readAllLines(Paths.get("D:\\code\\demo\\demo.txt"))
        //        .stream().mapToInt(e->Integer.parseInt(e.split(";")[1]))
        //        .sum();
        //System.out.println("sum: "+sum);

        ExecutorService service = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(()->"task1",()->"task2",()->"task3",()->"task4");
        try {
            service.invokeAll(callables).stream().map(f -> {
                try {
                    return f.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException();
            }).forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
