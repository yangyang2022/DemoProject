package com.yangyang.proxy;

import com.yangyang.log.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//1 创建一个类实现 InvocationHandler 接口
public class LoggerProxy implements InvocationHandler {
    private LoggerProxy(){}
    //2 创建一个代理对象 ,由于不知具体对象,用object代替
    private Object target;
    //3 创建一个方法来获取对象,方法参数就是 代理对象
    private static Object getInstance(Object o){
        //4.1 创建 loggerProxy 对象
        LoggerProxy proxy = new LoggerProxy();
        //4.2 设置代理对象
        proxy.target = o;
        //通过Proxy 来创建对象,此时result 就是一个代理对象
        Object result = Proxy.newProxyInstance(o.getClass().getClassLoader(),o.getClass().getInterfaces(),proxy);
        return result;
    }

    //代理对象所有操作都经过invoke
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //Logger.info("dosomething .... ");
        if(method.isAnnotationPresent(LogInfo.class)){
            Logger.info(method.getAnnotation(LogInfo.class).value());
        }
        Object obj = method.invoke(target,args);
        return obj;
    }
}
