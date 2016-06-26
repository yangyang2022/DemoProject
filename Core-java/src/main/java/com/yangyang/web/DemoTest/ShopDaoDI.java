package com.yangyang.web.DemoTest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ShopDaoDI {
    String value() default "";
}
