package com.yangyang.beanScanner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//actuator

public class Invoker {

    private Object target;
    private Method method;

    public Invoker() {
    }

    public Invoker(Object target, Method method) {
        this.target = target;
        this.method = method;
    }

    public static Invoker invokeOf(Object target,Method method){
        Invoker invoker = new Invoker(target,method);
        return invoker;
    }
    public Object invoke(Object[] args){
        try {
            return method.invoke(target,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
