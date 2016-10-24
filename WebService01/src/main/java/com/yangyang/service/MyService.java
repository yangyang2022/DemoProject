package com.yangyang.service;

import javax.jws.WebService;

@WebService(endpointInterface = "com.yangyang.service.IMyService")
public class MyService implements IMyService {
    @Override
    public int add(int a, int b) {
        System.out.println(a+" + "+b +" = "+(a+b));
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        System.out.println(a+" - "+b +" = "+(a-b));
        return a-b;
    }
}
