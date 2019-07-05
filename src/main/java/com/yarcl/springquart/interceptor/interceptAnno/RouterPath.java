package com.yarcl.springquart.interceptor.interceptAnno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RouterPath {

    Router value() default Router.ROUTER_PATH;

    enum Router {
        ROUTER_PATH,
        ROUTER_DISABLE
    }

}
