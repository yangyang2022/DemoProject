package com.yangyang.quartz;

import java.util.TimerTask;

public class HelloTask extends TimerTask{
    private HelloWorld helloWorld;
    @Override
    public void run() {
        helloWorld.sayHello();
    }
}
