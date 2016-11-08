package com.yangyang.multiThread;

public class PThread extends Thread{

    private ThreadPool pool;
    private boolean isShutdown = false;
    private boolean isIdel = false;
    private Runnable target;

    public PThread(Runnable target,String name,ThreadPool pool){
        super(name);
        this.target = target;
        this.pool = pool;
    }

    public Runnable getTarget() {
        return target;
    }

    public boolean isIdel() {
        return isIdel;
    }

    public synchronized void shutdown(){
        isShutdown = true;
        notifyAll();
    }
    public synchronized void setTarget(Runnable newTarget){
        target = newTarget;
        notifyAll();
    }
    @Override
    public void run() {
        while (!isShutdown){
            if(target != null) target.run();
            isIdel = true;
            pool.repool(this);
            try {
                synchronized (this){
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isIdel = false;
        }
    }
}
