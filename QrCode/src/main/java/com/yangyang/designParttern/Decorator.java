package com.yangyang.designParttern;

abstract class Component{
    abstract void doJob();
}
class ConcreteComponent extends Component{
    @Override
    void doJob() {
        System.out.println("i am from concreteComponent...i am closed for modified");
    }
}
abstract class DecoratorComponent extends Component{
    protected Component com;
    public DecoratorComponent(Component c){com = c;}
    public void doJob(){if (com != null) com.doJob();}
}
class ConcreteCom1 extends DecoratorComponent{
    public ConcreteCom1(Component c) {
        super(c);
    }

    @Override
    public void doJob() {
        super.doJob();
        System.out.println("decorate com ...");
    }
}
class ConcreteCom2 extends DecoratorComponent{
    public ConcreteCom2(Component c) {
        super(c);
    }

    @Override
    public void doJob() {
        super.doJob();
        System.out.println("decorete com ...");
    }
}

public class Decorator {

    public static void main(String[] args) {
        ConcreteComponent cm = new ConcreteComponent();
        ConcreteCom1 c1 = new ConcreteCom1(cm);
        ConcreteCom2 c2 = new ConcreteCom2(cm);

        c1.doJob();
        c2.doJob();
    }
}
