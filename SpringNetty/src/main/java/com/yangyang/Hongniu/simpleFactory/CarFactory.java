package com.yangyang.Hongniu.simpleFactory;

import java.io.IOException;
import java.util.Properties;

public class CarFactory {

    private static Properties props = new Properties();

    static {
        try {
            props.load(CarFactory.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("file not exited!");
        }
    }

    public static Car getCar() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String className = props.getProperty("car");
        return (Car) Class.forName(className).newInstance();
    }
    public static Car getInstance(Class clz){
        Car car = null;
        try {
            car = (Car) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }
}
