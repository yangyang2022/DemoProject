package com.yangyang.designParttern;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WorkerThread {
    public static void main(String[] args) {
        Helper helper = new Helper();
        helper.init();
        for (int i = 0; i < 100; ++i) {
            helper.submit("something .... ");
        }
    }
    static class Helper{
        private final BlockingQueue<String> workQueue = new ArrayBlockingQueue<String>(100);
        protected String doprocess(String task ){
            return task+" ->processed.";
        }

        private final Thread workThread = new Thread(){
            @Override
            public void run() {
                String task = null;
                while (true){
                    try {
                        task = workQueue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                    System.out.println(doprocess(task));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        public void init(){
            workThread.start();
        }
        public void submit(String task){
            try {
                workQueue.put(task);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
