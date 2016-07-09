package com.yangyang.annotation;

import org.springframework.stereotype.Component;

@Component
public class TestObject {
    public void info(){
        System.out.println("hello world --> TestObject ");
    }
}
