package com.yangyang.DesignPattern;


interface Target{
    void request();
}
class ConcreteTarget implements Target{
    @Override
    public void request() {
        System.out.println("if you need any help ,pls call me!");
    }
}
class Adaptee{
    //old service
    public void dosomething(){
        System.out.println("I'm kind of busy,leave me alone!");
    }
}
class Adapter extends Adaptee implements Target{
    @Override
    public void request() {
        super.dosomething();
    }
}
public class AdapterParttern {

    public static void main(String[] args) {

        Target target = new ConcreteTarget();
        target.request();
        target = new Adapter();
        target.request();
    }
}
