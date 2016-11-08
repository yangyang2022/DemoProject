package com.yangyang.multiThread;

import com.yangyang.Util.SleepUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {


    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semap = new Semaphore(5);

        for (int i = 0; i < 20; ++i) {
            final int no = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semap.acquire();
                        System.out.println("access: "+no);
                        SleepUtil.SleepMills((int) (Math.random()*3000));
                        semap.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        SleepUtil.sleep5s();
        service.shutdown();
    }

}
