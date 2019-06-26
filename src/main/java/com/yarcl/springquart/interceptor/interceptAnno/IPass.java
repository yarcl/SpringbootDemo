package com.yarcl.springquart.interceptor.interceptAnno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by xiaozhi on 2019/6/26.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface IPass {
    Auth value() default Auth.PASS;
    enum Auth{
        PASS,
        FILTER
    }
}
