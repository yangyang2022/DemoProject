package com.yangyang.multiThread;

import com.yangyang.Util.SleepUtil;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3,()-> System.out.println("ok ! all ready!"));

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new Thread(new Runner(barrier,"number 1")));
        service.submit(new Thread(new Runner(barrier,"number 2")));
        service.submit(new Thread(new Runner(barrier,"number 3")));

        SleepUtil.sleep10s();
        service.shutdown();
    }
    static class Runner implements Runnable{
        private CyclicBarrier barrier;
        private String name;

        public Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }
        @Override
        public void run() {
            SleepUtil.SleepSecond((new Random()).nextInt(5));
            System.out.println(name+" ready!");
            try {
                System.out.println(Thread.currentThread().getName() + " arrive," + barrier.getNumberWaiting());
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(name + " run ...");
        }
    }
}
