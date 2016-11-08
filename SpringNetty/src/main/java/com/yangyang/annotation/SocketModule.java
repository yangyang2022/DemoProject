package com.yangyang.annotation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface SocketModule {
    //module
    short module() default (short)0;
}
