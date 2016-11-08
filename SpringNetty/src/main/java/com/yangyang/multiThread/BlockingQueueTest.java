package com.yangyang.multiThread;

import com.yangyang.Util.SleepUtil;

import java.util.concurrent.*;

public class BlockingQueueTest {

    public class Basket{
        //capcity is 3
        BlockingQueue<String> basket = new LinkedBlockingQueue<>(3);

        //product
        public void produce() throws InterruptedException {
            basket.put("a apple");
        }

        //consumer
        public String consumer() throws InterruptedException {
            return basket.take();
        }
    }
    class Consumer implements Runnable{
        private String intance;
        private Basket basket;

        public Consumer(String intance, Basket basket) {
            this.intance = intance;
            this.basket = basket;
        }

        @Override
        public void run() {
            try {
                while (true){
                    //consumer apple
                    System.out.println(basket.consumer());
                    System.out.println("consumer over: "+intance);
                    //sleep 1000ms
                    SleepUtil.SleepMills(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class Producer implements Runnable{
        private String instance;
        private Basket basket;

        public Producer(String instance, Basket basket) {
            this.instance = instance;
            this.basket = basket;
        }

        @Override
        public void run() {
            try {
                while (true){
                    basket.produce();
                    System.out.println("produce apple over: "+instance);
                    //sleep 300ms
                    SleepUtil.SleepMills(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueueTest test = new BlockingQueueTest();
        Basket basket = test.new Basket();

        ExecutorService service = Executors.newCachedThreadPool();

        Producer producer1 = test.new Producer("produce 001", basket);
        Producer producer2 = test.new Producer("produce 002", basket);
        Consumer consumer = test.new Consumer("consumer 001", basket);

        service.submit(producer1);
        service.submit(producer2);
        service.submit(consumer);
    }
}
