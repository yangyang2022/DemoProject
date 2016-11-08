package com.yangyang.DesignPattern;

interface Human{
    void getColor();
    void talk();
}
class BlackMan implements Human{
    @Override
    public void getColor() {
        System.out.println("balck skin ...");
    }

    @Override
    public void talk() {
        System.out.println("black man say ...");
    }
}
class YellowMan implements Human{
    @Override
    public void getColor() {
        System.out.println("yellow man say ...");
    }

    @Override
    public void talk() {
        System.out.println("yellow man say ...");
    }
}
class WhiteMan implements Human{
    @Override
    public void getColor() {
        System.out.println("white man say ...");
    }

    @Override
    public void talk() {
        System.out.println("white man say ...");
    }
}
abstract class AbstractManFactory{
    public abstract <T extends Human> T createHuman(Class<T> clz);
}
class HumanFactory extends AbstractManFactory{
    @Override
    public <T extends Human> T createHuman(Class<T> clz) {
        Human human = null;
        try {
            human = (Human) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) human;
    }
}
public class FactoryMethod {

    public static void main(String[] args) {

        AbstractManFactory lu = new HumanFactory();

        Human whiteMan = lu.createHuman(WhiteMan.class);
        whiteMan.getColor();
        whiteMan.talk();

        Human yellowMan = lu.createHuman(YellowMan.class);
        yellowMan.getColor();
        yellowMan.talk();


    }
}
