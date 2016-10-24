package com.yangyang.designParttern;

abstract class Subject_proxy{
    abstract void doSomething();
}
class ConcreteSubejct extends Subject_proxy{
    @Override
    void doSomething() {
        System.out.println("i am concrete subject ...");
    }
}
class Proxy_subject extends Subject_proxy{
    private ConcreteSubejct cs;

    @Override
    void doSomething() {
        System.out.println("proxy calling ... ");
        if(cs == null) cs = new ConcreteSubejct();
        cs.doSomething();
    }
}
public class Proxy {

    public static void main(String[] args) {
        Proxy_subject proxy = new Proxy_subject();
        proxy.doSomething();
    }
}
