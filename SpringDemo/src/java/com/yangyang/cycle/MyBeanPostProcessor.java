package com.yangyang.cycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 *  实现这个 BeanPostProcessor
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("befaor ... "+s);

        if("car".equals(s)){
            //...
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("after ... "+o);
        CarDemo carDemo = new CarDemo();
        carDemo.setBrand("Baomoooooooooooo");

        return o;

    }
}
