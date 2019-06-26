package com.yarcl.springquart.interceptor.config;

import com.yarcl.springquart.interceptor.enumAnno.IPass;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiaozhi on 2019/6/26.
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        IPass methodAnnotation = handlerMethod.getMethodAnnotation(IPass.class);
        return methodAnnotation != null && methodAnnotation.value().equals(IPass.Auth.PASS);
    }


}
