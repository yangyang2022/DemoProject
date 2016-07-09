package com.yangyang.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂的方法 (先创建工厂本身,在调用工厂的实例方法来返回bean实例 )
 */
public class InstanceCarFactory {
    private Map<String,Car> cars = null;

    public InstanceCarFactory(){
        cars = new HashMap<>();
        cars.put("audi",new Car("audi",30_0000));
        cars.put("ford",new Car("ford",40_0000));
    }
    public Car getCar(String name){
        return cars.get(name);
    }
}
