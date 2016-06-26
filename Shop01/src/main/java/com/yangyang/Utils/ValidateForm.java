package com.yangyang.Utils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateForm {
    ValidateType type();
    String errorMsg();
    String value() default "";
}
