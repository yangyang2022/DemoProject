package com.yangyang.DesignPattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IGamePlayer{
    void login(String user,String password);
    void killBoss();
    void upgreade();
}
class GamePlayer implements IGamePlayer{
    private String name = "";

    public GamePlayer(String name) {
        this.name = name;
    }

    @Override
    public void login(String user, String password) {
        System.out.println(name+" login!");
    }

    @Override
    public void killBoss() {
        System.out.println(name+" is kill boss!");
    }

    @Override
    public void upgreade() {
        System.out.println(name + " is upgread!");
    }
}
class GameHI implements InvocationHandler{
    private Class clz = null;
    private Object object = null;

    public GameHI(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before ...");
        Object result = method.invoke(this.object,args);
        System.out.println("after ...");

        return result;
    }
}
public class DynamicProxy {

    public static void main(String[] args) {

        IGamePlayer player = new GamePlayer("yangyang");
        InvocationHandler handler = new GameHI(player);

        ClassLoader classLoader = player.getClass().getClassLoader();
        IGamePlayer proxy = (IGamePlayer) Proxy.newProxyInstance(classLoader,player.getClass().getInterfaces(),handler);

        proxy.login("yangyang","123123");
        proxy.killBoss();
        proxy.upgreade();
    }
}
