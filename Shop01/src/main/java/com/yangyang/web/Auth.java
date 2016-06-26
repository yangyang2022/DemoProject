package com.yangyang.web;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {
    // user ->登录用户可以访问
    // any -> 任何用户可以访问
    // -- 没有注解的的都是 admin 访问
    String value() default "user";
}
