package com.yangyang.aop.helloworld;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class CalculatorProxy {
    private ICalculator target;

    public CalculatorProxy(ICalculator target) {
        this.target = target;
    }

    public ICalculator getProxy(){
        ICalculator proxy = null;

        //代理对象由哪一个类进行加载
        ClassLoader loader = target.getClass().getClassLoader();

        //代理对象的类型,即其中有哪些类型
        Class[] interfaces = new Class[]{ICalculator.class};

        //当调用代理对象其中方法时,该执行哪些方法?
        InvocationHandler handler = new InvocationHandler() {
            /**
             *
             * @param proxy 正在返回的对象,一般在invoke方法中很少使用(容易内存溢出,无限循环)
             * @param method 正在被调用的方法
             * @param args
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("begin [ "+method.getName()+" ]... ");

                Object result = null;
                try {
                    //前置通知
                    result = method.invoke(target,args);
                    //返回通知,可以访问结果
                } catch (Exception e) {
                    //异常通知
                    e.printStackTrace();
                }
                //后置通知
                return method.invoke(target,args);
            }
        };
        proxy = (ICalculator) Proxy.newProxyInstance(loader,interfaces,handler);

        return proxy;
    }
}
