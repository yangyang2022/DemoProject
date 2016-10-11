package com.yangyang.PoiUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelResource {
    String title() default "默认标题";
    int order() default 9999;
}
