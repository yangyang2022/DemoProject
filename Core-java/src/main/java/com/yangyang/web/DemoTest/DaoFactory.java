package com.yangyang.web.DemoTest;

import com.yangyang.web.Util.PropUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class DaoFactory {

    public static void injectDao(Object object){
        Method[] methods = object.getClass().getDeclaredMethods();
        for(Method m:methods){
            if(m.isAnnotationPresent(ShopDaoDI.class)){
                String v = m.getDeclaredAnnotation(ShopDaoDI.class).value();
                if(v == null || "".equals(v)){
                    v = m.getName().substring(3);
                    v = v.substring(0,1).toLowerCase()+v.substring(1);
                }
                Object obj = DaoFactory.createFactory().getDao(v);
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
    public static IFactory createFactory(){
        Properties prop = PropUtils.getDaoProperties();
        String fs = prop.getProperty("factory");
        IFactory f = null;
        try {
            Class clz = Class.forName(fs);
            String method = "getInstance";
            Method m = null;
            try {
                m = clz.getMethod(method);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            f = (IFactory)m.invoke(clz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return f;
    }
}
