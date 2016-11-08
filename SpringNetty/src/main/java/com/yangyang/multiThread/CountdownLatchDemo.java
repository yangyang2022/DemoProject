package com.yangyang.multiThread;

import com.yangyang.Util.SleepUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchDemo {

    private static final int PLAYER_AMOUNT = 5;

    static class Player implements Runnable{
        private int id;
        private CountDownLatch begin ;
        private CountDownLatch end ;

        public Player(int id, CountDownLatch begin, CountDownLatch end) {
            this.id = id;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public void run() {
            try {
                begin.await();
                Thread.sleep((long) (Math.random()*100));
                System.out.println("player " + id + "arrived");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                end.countDown();
            }
        }
    }

    /**
     *
     * has some problem
     * @param args
     */
    public static void main(String[] args) {
        //TODO
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);

        Player[] players = new Player[PLAYER_AMOUNT];
        for (int i = 0; i < PLAYER_AMOUNT; ++i) {
            players[i] = new Player(i + 1, begin, end);
        }
        new Thread(()->{
            int i = 0;
            while (true){
                System.out.println("--> "+(++i));
                SleepUtil.sleep1s();
            }
        }).start();

        ExecutorService service = Executors.newFixedThreadPool(PLAYER_AMOUNT);
        for(Player player : players){
            service.submit(player);
            System.out.println("Race begin");
            begin.countDown();
            try {
                end.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("Race end");
            }
            service.shutdown();
        }

    }
}
