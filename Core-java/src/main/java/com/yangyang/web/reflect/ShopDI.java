package com.yangyang.web.reflect;

/*
    @ShopDI("userDao) 表示注入userDao
    @ShopDI 表示 setXXX 注入
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ShopDI {
    String value() default "";
}
