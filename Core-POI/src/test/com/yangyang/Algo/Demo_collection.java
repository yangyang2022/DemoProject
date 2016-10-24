package com.yangyang.Algo;

import com.algs4.stdlib.In;
import com.algs4.stdlib.StdRandom;
import com.yangyang.utils.Bag;
import com.yangyang.utils.Queue;
import org.junit.Test;

public class Demo_collection {

    private static void setDoubleData(Bag<Double> item,int length){
        while (length-- > 0 ) item.add(StdRandom.uniform(0.0,100.0));
    }
    private static void showBag(Bag<Double> bag){
        for(Double d : bag) System.out.print(d+" ");
        System.out.println();
    }
    private static void showArray(int[] arr){
        for(Integer i : arr) System.out.print(i+" ");
        System.out.println();
    }
    @Test
    public void testDemo1() {
        Bag<Double> numbers = new Bag<>();

        setDoubleData(numbers,10);
        showBag(numbers);
        System.out.println("size: "+numbers.size());
    }

    private static int[] readInts(String name){
        In in = new In(name);
        Queue<Integer> q = new Queue<>();
        while (!in.isEmpty())
            q.enqueue(in.readInt());
        int[] a = new int[q.size()];
        for(int i=0;i<q.size();i++)
            a[i] = q.dequeue();
        return a;
    }
    @Test
    public void testQueue() {

        String filename = "C:\\mavenProject\\DemoProject\\Core-javaTest\\src\\resource\\data.txt";

        showArray(readInts(filename));

    }
}
