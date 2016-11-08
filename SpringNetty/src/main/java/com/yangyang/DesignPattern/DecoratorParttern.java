package com.yangyang.DesignPattern;

abstract class SchoolReport{
    public abstract void report();
    public abstract void sign(String name);
}

abstract class Decorator extends SchoolReport{
    private SchoolReport sr;

    public Decorator(SchoolReport sr) {
        this.sr = sr;
    }

    @Override
    public void report() {
        this.sr.report();
    }

    @Override
    public void sign(String name) {
        this.sr.sign(name);
    }
}
class HighScoreDecorator extends Decorator{

    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportHigh(){
        System.out.println("high: 99 99 99 98");
    }
    @Override
    public void report() {
        this.reportHigh();
        super.report();
    }

    @Override
    public void sign(String name) {

    }
}
public class DecoratorParttern {

    public static void main(String[] args) {


    }
}
