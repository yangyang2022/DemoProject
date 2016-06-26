package com.yangyang.web.Test;

import com.yangyang.web.reflect.ShopDI;
import org.junit.Test;

import java.lang.reflect.Method;

public class TestAnnotation {

    @ShopDI("userDao")
    public void aa(){

    }
    @ShopDI
    public void setUserDao(){

    }

    @Test
    public void testAnnotation1() {
        Method[] methods = this.getClass().getDeclaredMethods();
        for(Method m : methods){
            if(m.isAnnotationPresent(ShopDI.class)){
                ShopDI di = m.getDeclaredAnnotation(ShopDI.class);
                String v = di.value();
                if(v == null ||"".equals(v)){
                    v = m.getName().substring(3);
                    v = v.substring(0,1).toLowerCase() + v.substring(1);
                }
                System.out.println(v);
            }
        }
    }
}
