package com.yangyang.DesignPattern;


@FunctionalInterface
interface IStrategy{
    void operate();
}
class BackDoor implements IStrategy{
    @Override
    public void operate() {
        System.out.println("back door ...");
    }
}

class GiveGreenLight implements IStrategy{
    @Override
    public void operate() {
        System.out.println("give green light ...");
    }
}
class BlockEnemy implements IStrategy{
    @Override
    public void operate() {
        System.out.println("block enrmy ...");
    }
}

class Context{

    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }
    public void operate(){
        this.strategy.operate();
    }
}

enum Calculator{
    ADD("+"){
        @Override
        public int exec(int a, int b) {
            return a+b;
        }
    },
    SUB("-"){
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    }
    ;
    String value = "";

    Calculator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public abstract int exec(int a,int b);
}
public class StrategyParttern {

    public static void main(String[] args) {

        //Context context = new Context(new BackDoor());
        //context.operate();
        //context = new Context(new GiveGreenLight());
        //context.operate();
        //context = new Context(new BlockEnemy());
        //context.operate();

        //Context context = new Context(()-> System.out.println("bacl door"));
        //context.operate();
        //context = new Context(()-> System.out.println("give green light"));
        //context.operate();

        System.out.println(Calculator.ADD.exec(1,2));
        System.out.println(Calculator.SUB.exec(3, 66));

    }
}
