package com.yangyang.DesignPattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class Singleton{
    private Singleton(){}
    public void doSomething(){}
}
class Singletonactory{
    private static Singleton singleton;
    static {
        try {
            Class clz = Class.forName(Singleton.class.getName());
            Constructor constructor = clz.getDeclaredConstructor();
            constructor.setAccessible(true);
            singleton = (Singleton) constructor.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static Singleton getInstance(){return singleton;}
}

public class FactorySingle {

    public static void main(String[] args) {
    }
}
