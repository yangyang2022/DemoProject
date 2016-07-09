package com.yangyang.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 静态工厂方法: 直接调用摸一个类的静态方法就能返回Bean的实例
 */
public class StaticCarFactory {
    private static Map<String ,Car> cars = new HashMap<>();
    static {
        cars.put("audi",new Car("audi",30_0000));
        cars.put("ford",new Car("ford",40_0000));
    }
    //静态工厂方法
    public static Car getCar(String name){
        return cars.get(name);
    }
}
