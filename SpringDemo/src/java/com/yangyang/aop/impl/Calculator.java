package com.yangyang.aop.impl;

import org.springframework.stereotype.Component;

@Component
public class Calculator implements ICalculator {
    @Override
    public int add(int i, int j) {

        return i+j;
    }

    @Override
    public int sub(int i, int j) {
        return i-j;
    }

    @Override
    public int div(int i, int j) {
        if(j != 0){
            return i/j;
        }
        throw new RuntimeException("输入不合法!");
        //System.out.println("输入不合法!");
        //return Integer.MIN_VALUE;
    }

    @Override
    public int mul(int i, int j) {
        return i * j;
    }
}
