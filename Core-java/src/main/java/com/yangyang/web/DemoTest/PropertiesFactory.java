package com.yangyang.web.DemoTest;

import com.yangyang.web.Util.PropUtils;

import java.util.Properties;

public class PropertiesFactory implements IFactory {
    private static PropertiesFactory factory = new PropertiesFactory();
    private PropertiesFactory(){}

    public static IFactory getInstance(){
        return factory;
    }
    @Override
    public Object getDao(String name) {
        Properties prop = PropUtils.getDaoProperties();
        String daoName = prop.getProperty(name);
        Object obj = null;
        try {
            obj = Class.forName(daoName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
