package com.yangyang.DesignPattern;

interface Human_1 {
    void getColor();
    void talk();
    void getSex();
}
abstract class AbstractWhiteHuman implements Human_1{
    @Override
    public void getColor() {
        System.out.println("white skin ...");
    }

    @Override
    public void talk() {
        System.out.println("white talk ...");
    }

    //sex
}
abstract class AbstractBlackHuman implements Human_1{
    @Override
    public void getColor() {
        System.out.println("black man ...");
    }

    @Override
    public void talk() {
        System.out.println("black man say ...");
    }
}
abstract class AbstractYellowHuman implements Human_1{
    @Override
    public void getColor() {
        System.out.println("Yellow man ...");
    }

    @Override
    public void talk() {
        System.out.println("Yellow man say ...");
    }
}
class FemaleYelloHuman extends AbstractYellowHuman{
    @Override
    public void getSex() {
        System.out.println("yellow female sex");
    }
}
class MeleYelloHuman extends AbstractYellowHuman{
    @Override
    public void getSex() {
        System.out.println("yellow man sex");
    }
}
interface HumanFactory_1{
    Human_1 createYellowHuman();
    Human_1 createBlackHuman();
    Human_1 createWhiteHuman();
}
class Femaleactory implements HumanFactory_1{
    @Override
    public Human_1 createYellowHuman() {
        return null;
    }

    @Override
    public Human_1 createBlackHuman() {
        return null;
    }

    @Override
    public Human_1 createWhiteHuman() {
        return null;
    }
}
public class AbstractFactory {

    public static void main(String[] args) {

        System.out.println("hello world");

    }
}
