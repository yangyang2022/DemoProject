package com.yangyang.DesignPattern;

abstract class HumanModel{
    protected abstract void start();
    protected abstract void alarm();
    protected abstract void stop();
    protected abstract void enginBoom();
    final public void run(){
        this.start();
        this.enginBoom();
        if(this.isAlarm()){
            this.alarm();
        }
        this.stop();
    }
    protected boolean isAlarm(){return true;}
}
class HumanModel1 extends HumanModel{
    private boolean alarmFlag = true;

    public void setAlarmFlag(boolean alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    public boolean isAlarmFlag() {
        return alarmFlag;
    }

    @Override
    protected void start() {
        System.out.println("start ...");
    }

    @Override
    protected void alarm() {
        System.out.println("alarm ...");
    }

    @Override
    protected void stop() {
        System.out.println("stop ...");
    }

    @Override
    protected void enginBoom() {
        System.out.println("enginboom ...");
    }

    @Override
    protected boolean isAlarm() {
        return this.alarmFlag;
    }
}
public class TemplateMethod {
    public static void main(String[] args) {

        HumanModel1 bmw = new HumanModel1();
        bmw.run();

        System.out.println("===========================================");

        HumanModel1 bmw2 = new HumanModel1();
        bmw2.setAlarmFlag(false);
        bmw2.run();
    }
}
