package com.yangyang.multiThread;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    private int size;
    private Object[] elements;
    private StampedLock lock = new StampedLock();

    public Object get(int n){
        long stamp = lock.tryOptimisticRead();
        Object[] curremtElements = elements;
        int currentSize = size;
        if(!lock.validate(stamp)){
            stamp = lock.readLock();
            curremtElements = elements;
            currentSize = size;
            lock.unlock(stamp);
        }
        return n < currentSize ? curremtElements[n] : null;
    }

}
