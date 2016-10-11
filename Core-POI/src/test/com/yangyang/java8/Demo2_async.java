package com.yangyang.java8;

import org.junit.Test;

interface Task{
    void execute();
}

public class Demo2_async {

    private static void doSomething(Runnable r) {r.run();}
    private static void doSomething(Task t){t.execute();}

    @Test
    public void testDemo1() {
        doSomething((Task) ()-> System.out.println("hello world"));
    }

    @Test
    public void testDemo2() {
        //strategy
        //Validator numberValidate = new Validator(new isNumber());
        //System.out.println(numberValidate.validate("aaaa"));
        //System.out.println(numberValidate.validate("123123"));
        //Validator lowerValidator = new Validator(new isAllLowerCase());
        //System.out.println(lowerValidator.validate("aaa"));
        //System.out.println(lowerValidator.validate("123123"));

        Validator validateNumber = new Validator((s)->s.matches("\\d+"));
        System.out.println(validateNumber.validate("123"));
        System.out.println(validateNumber.validate("aaa"));

    }
}
interface validateStrategy{
    boolean execute(String str);
}
class isAllLowerCase implements validateStrategy{
    @Override
    public boolean execute(String str) {
        return str.matches("[a-z]+");
    }
}
class isNumber implements validateStrategy{
    @Override
    public boolean execute(String str) {
        return str.matches("\\d+");
    }
}
class Validator{
    private validateStrategy strategy;

    public Validator(validateStrategy strategy) {
        this.strategy = strategy;
    }
    public boolean validate(String s){
        return strategy.execute(s);
    }
}
