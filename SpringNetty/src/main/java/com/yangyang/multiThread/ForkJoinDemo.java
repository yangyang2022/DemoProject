package com.yangyang.multiThread;

import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long>{

    private static final int THRESHOLD = 100;
    private long start;
    private long end;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        if((end - start) <THRESHOLD){
            for (long i = start; i < end; ++i) {
                sum += i;
            }
        }else {
            long mid = (start+end)/2;
            ForkJoinDemo left = new ForkJoinDemo(start,mid);
            ForkJoinDemo right = new ForkJoinDemo(mid,end);
            left.fork();right.fork();
            sum = left.join() + right.join();
        }
        return sum;
    }
}
