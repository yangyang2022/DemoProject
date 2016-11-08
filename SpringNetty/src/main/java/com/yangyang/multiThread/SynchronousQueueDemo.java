package com.yangyang.multiThread;

import com.yangyang.Util.SleepUtil;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueDemo {


    public static void main(String[] args) {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Consumer(queue).start();
        new Product(queue).start();
    }

    static class Consumer extends Thread{
        private SynchronousQueue<Integer> queue;
        public Consumer(SynchronousQueue<Integer> queue){
            this.queue = queue;
        }
        @Override
        public void run() {
            while (true){
                try {
                    System.out.println("comsumer: "+queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("==================== ...");
            }
        }
    }
    static class Product extends Thread{
        private SynchronousQueue<Integer> queue;
        public Product(SynchronousQueue<Integer> queue){
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true){
                int rand = new Random().nextInt(1000);
                System.out.println("product: "+rand);
                System.out.println("waiting 3s ...");
                SleepUtil.sleep3s();
                queue.offer(rand);
            }
        }
    }
}
