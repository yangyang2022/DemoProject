package com.yangyang.multiThread;

import java.util.concurrent.*;

public class CourrentLinkQueue {
    private static ConcurrentLinkedDeque<Integer> queue = new ConcurrentLinkedDeque<>();
    private static int count = 2;//thread number
    private static CountDownLatch latch = new CountDownLatch(count);

    //product
    public static void offer(){
        for (int i = 0; i < 100_000; ++i) {
            queue.offer(i);
        }
    }
    //consumer
    static class Poll implements Runnable{
        @Override
        public void run() {
            while (!queue.isEmpty()){
                System.out.println(queue.poll());
            }
            latch.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService service = Executors.newFixedThreadPool(4);

        CourrentLinkQueue.offer();
        for (int i = 0; i < count; ++i) {
            service.submit(new Poll());
        }
        latch.await();
        System.out.println("cost time: "+(System.currentTimeMillis()-start));
        service.shutdown();
    }
}
