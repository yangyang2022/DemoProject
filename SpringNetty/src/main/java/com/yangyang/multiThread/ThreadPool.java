package com.yangyang.multiThread;

import java.util.List;
import java.util.Vector;

public class ThreadPool {

    private static ThreadPool instance = null;
    private boolean isShutdown = false;

    private List<PThread> idelThreads ;
    private int threadCounter ;

    public ThreadPool() {
        idelThreads = new Vector<>(5);
        threadCounter = 0;
    }

    public synchronized static ThreadPool getInstance() {
        if(instance == null) instance = new ThreadPool();
        return instance;
    }

    public int getThreadCounter() {
        return threadCounter;
    }
    public synchronized void repool(PThread repoolingThread){
        if(!isShutdown)
            idelThreads.add(repoolingThread);
        repoolingThread.shutdown();
    }
    public synchronized void shutdown(){
        isShutdown = true;
        idelThreads.forEach(pThread -> pThread.shutdown());
    }
    public synchronized void start(Runnable target){
        PThread thread = null;
        if(idelThreads.size() > 0){
            int lastIndex = idelThreads.size()-1;
            thread = idelThreads.get(lastIndex);
            thread.setTarget(target);
        }else {
            threadCounter++;
            thread = new PThread(target,"Pthread#"+threadCounter,this);
            thread.start();
        }
    }
}
