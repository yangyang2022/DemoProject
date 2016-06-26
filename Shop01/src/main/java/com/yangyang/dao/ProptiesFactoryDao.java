package com.yangyang.dao;

import com.yangyang.Utils.PropUtils;

import java.util.Properties;

public class ProptiesFactoryDao implements IFactorDao{
    private static ProptiesFactoryDao factoryDao = new ProptiesFactoryDao();
    private ProptiesFactoryDao(){}

    public static IFactorDao getInstance(){
        return factoryDao;
    }
    @Override
    public Object getDao(String name) {
        Object obj = null;
        Properties prop = PropUtils.getDaoFacoryProperties();
        try {
            Class clz = Class.forName(prop.getProperty(name));
            obj = clz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
