package com.yangyang.model;

import com.yangyang.Utils.PropUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class DaoFactory {

    public static void injectDao(Object object){
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method m:methods){
            if(m.isAnnotationPresent(ShopDI.class)){
                String v = m.getAnnotation(ShopDI.class).value();
                if(v == null || "".equals(v)){
                    v = m.getName().substring(3);
                    v = v.substring(0,1).toLowerCase()+v.substring(1);
                }
                Object obj = DaoFactory.createFactor().getDao(v);
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

    public static IFactorDao createFactor(){
        Properties prop = PropUtils.getDaoFacoryProperties();
        IFactorDao f = null;
        try {
            Class clz = Class.forName(prop.getProperty("factory"));
            String method = "getInstance";
            Method m = clz.getMethod(method);
            f = (IFactorDao) m.invoke(clz);
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
