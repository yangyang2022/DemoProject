package com.yangyang.itcast;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MyQueue {

    //container
    private List<Object> list = new LinkedList<>();

    //counter
    private AtomicInteger count = new AtomicInteger(0);

    //ceil
    private final int miniSize = 0;
    private final int maxSize ;

    //constructor
    public MyQueue(int size){
        this.maxSize = size;
    }

    public static void main(String[] args) {


    }

}
