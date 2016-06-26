package com.yangyang.web.Util;

import com.yangyang.web.reflect.IFactoryDao;
import com.yangyang.web.reflect.ShopDI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class DaoUtil {
    public static void injectDao(Object object){
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method m:methods){
            if(m.isAnnotationPresent(ShopDI.class)){
                ShopDI sd = m.getAnnotation(ShopDI.class);
                String v = sd.value();
                if(v == null || "".equals(v)){
                    v = m.getName().substring(3);
                    v = v.substring(0,1).toLowerCase()+v.substring(1);
                }
                Object obj = DaoUtil.createDaoFactory().getDao(v);
                try {
                    m.invoke(object,obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void injectDao1(Object object){
        Method[] mds = object.getClass().getDeclaredMethods();
        for (Method d:mds){
            String name = d.getName();
            if(name.startsWith("set")){
                name = name.substring(3);
                name = name.substring(0,1).toLowerCase() + name.substring(1);
                Object obj =  DaoUtil.createDaoFactory().getDao(name);
                try {
                    d.invoke(object,obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static IFactoryDao createDaoFactory(){
        Properties prop = PropUtils.getDaoProperties();
        IFactoryDao f = null;
        String fs = prop.getProperty("factory");
        try {
            Class clz = Class.forName(fs);
            String method = "getInstance";
            Method m = clz.getMethod(method);
            f = (IFactoryDao)m.invoke(clz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return f;
    }
}
